package net.Arathain.gatesofoblivion.mixin;

import net.Arathain.gatesofoblivion.entity.dream.BoundAttackWithBinderGoal;
import net.Arathain.gatesofoblivion.entity.dream.BoundFollowBinderGoal;
import net.Arathain.gatesofoblivion.entity.dream.BoundTrackAttackerGoal;
import net.Arathain.gatesofoblivion.entity.dream.BoundZombieAttackGoal;
import net.Arathain.gatesofoblivion.entity.interphace.BoundEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.UUID;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity implements BoundEntity {
    private static final TrackedData<Byte> TAMEABLE = DataTracker.registerData(TameableEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Optional<UUID>> BINDER_UUID = DataTracker.registerData(TameableEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);

    protected ZombieEntityMixin(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void initGoals() {
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(3, new BoundFollowBinderGoal( this, 1.0D, 10.0F, 2.0F, true));
        this.goalSelector.add(2, new BoundZombieAttackGoal(this, 1.0D, false));

        this.targetSelector.add(3, (new RevengeGoal(this)).setGroupRevenge(ZombifiedPiglinEntity.class));
        this.targetSelector.add(1, new BoundTrackAttackerGoal(this));
        this.targetSelector.add(2, new BoundAttackWithBinderGoal(this));
        this.targetSelector.add(2, new FollowTargetGoal(this, PlayerEntity.class, 10, true, false, (entity) -> !isTamed()));
        this.targetSelector.add(3, new FollowTargetGoal(this, MerchantEntity.class, false));
        this.targetSelector.add(3, new FollowTargetGoal(this, IronGolemEntity.class, true));
        this.targetSelector.add(5, new FollowTargetGoal(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
    }



    @Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo cir) {
        if (this.getOwnerUuid() != null) {
            tag.putUuid("Binder", this.getOwnerUuid());
        }
    }
    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo cir) {
        super.readCustomDataFromTag(tag);
        UUID ownerUUID;
        if (tag.containsUuid("Binder")) {
            ownerUUID = tag.getUuid("Binder");
        } else {
            String string = tag.getString("Binder");
            ownerUUID = ServerConfigHandler.getPlayerUuidByName(this.getServer(), string);
        }

        if (ownerUUID != null) {
            try {
                this.setOwnerUuid(ownerUUID);
                this.setTamed(true);
            } catch (Throwable var4) {
                this.setTamed(false);
            }
        }
    }
    @Inject(method = "initDataTracker", at = @At("TAIL"))
    protected void initDataTracker(CallbackInfo ci) {
        dataTracker.startTracking(TAMEABLE, (byte) 0);
        dataTracker.startTracking(BINDER_UUID, Optional.empty());
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


