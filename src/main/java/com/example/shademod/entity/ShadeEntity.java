package com.example.shademod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

/**
 * Тень: враждебный моб. Выходит из темноты, бьёт сильно, накладывает "Тьму" и замедление,
 * но горит на солнце, как зомби.
 */
public class ShadeEntity extends HostileEntity {

    public ShadeEntity(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);
        this.experiencePoints = 10;
    }

    public static DefaultAttributeContainer.Builder createShadeAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.29)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.3);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.15, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 10.0f));
        this.goalSelector.add(7, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tickMovement() {
        // Горит на солнце
        if (this.isAlive() && this.isAffectedByDaylight()) {
            this.setOnFireFor(8.0f);
        }
        super.tickMovement();
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean hit = super.tryAttack(target);
        if (hit && target instanceof LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 160, 0), this);
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 0), this);
        }
        return hit;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ENDERMAN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ENDERMAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDERMAN_DEATH;
    }
}
