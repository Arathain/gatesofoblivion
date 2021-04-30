package net.Arathain.gatesofoblivion.entity;

import net.Arathain.gatesofoblivion.entity.dream.*;
import net.Arathain.gatesofoblivion.entity.interphace.BoundEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class MordicantEntity extends ZombieEntity implements BoundEntity {
    private static final TrackedData<Byte> TAMEABLE = DataTracker.registerData(TameableEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Optional<UUID>> BINDER_UUID = DataTracker.registerData(TameableEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);


    public MordicantEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0);
        setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0);
        experiencePoints = 20;
    }

    @Override
    public void initGoals() {
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(3, new MordicantFollowAttackerGoal( this, 1.0D, 10.0F, 2.0F, true));
        this.goalSelector.add(2, new BoundZombieAttackGoal(this, 1.0D, false));

        this.targetSelector.add(3, (new RevengeGoal(this)).setGroupRevenge(ZombifiedPiglinEntity.class));
        this.targetSelector.add(1, new BoundTrackAttackerGoal(this));
        this.targetSelector.add(2, new BoundAttackWithBinderGoal(this));
        this.targetSelector.add(2, new FollowTargetGoal(this, PlayerEntity.class, 10, true, false, (entity) -> !isTamed()));
        this.targetSelector.add(3, new FollowTargetGoal(this, MerchantEntity.class, false));
        this.targetSelector.add(3, new FollowTargetGoal(this, IronGolemEntity.class, true));
        this.targetSelector.add(5, new FollowTargetGoal(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
    }
    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 200).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6).add(EntityAttributes.GENERIC_ARMOR, 6).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.75).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 350.0);
    }
    @Override
    public void tick() {
        super.tick();
        if (!world.isClient && (this.getHealth() <= this.getMaxHealth()/2)) {
            this.setOnFireFor(10);
            this.clearStatusEffects();
        }
        world.addParticle(ParticleTypes.FLAME,
                getX() + random.nextGaussian() * 0.2,
                getY() + random.nextGaussian() * 0.5 + 1,
                getZ() + random.nextGaussian() * 0.2,
                0, 0.2, 0);
        if (this.isInsideWaterOrBubbleColumn()) {
            world.addParticle(ParticleTypes.SMOKE,
                    getX() + random.nextGaussian() * 0.2,
                    getY() + random.nextGaussian() * 0.5 + 1,
                    getZ() + random.nextGaussian() * 0.2,
                    0, 0.2, 0);
        }
    }

    @Override
    public UUID getOwnerUuid() {
        return (UUID) ((Optional) this.dataTracker.get(BINDER_UUID)).orElse(null);
    }


    @Override
    public void setOwnerUuid(@Nullable UUID uuid) {
        this.dataTracker.set(BINDER_UUID, Optional.ofNullable(uuid));
    }

    @Override
    public void setOwner(PlayerEntity player) {
        this.setTamed(true);
        this.setOwnerUuid(player.getUuid());
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        try {
            UUID uUID = this.getOwnerUuid();
            return uUID == null ? null : this.world.getPlayerByUuid(uUID);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    @Override
    public boolean isOwner(LivingEntity entity) {
        return entity == this.getOwner();
    }

    @Override
    public boolean isTamed() {
        return (this.dataTracker.get(TAMEABLE) & 4) != 0;
    }

    @Override
    public void setTamed(boolean tamed) {
        byte b = this.dataTracker.get(TAMEABLE);
        if (tamed) {
            this.dataTracker.set(TAMEABLE, (byte) (b | 4));
        } else {
            this.dataTracker.set(TAMEABLE, (byte) (b & -5));
        }

        this.onTamedChanged();
    }
    public void reset() {
        this.setTarget(null);
        this.navigation.stop();
    }


}
