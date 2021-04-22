package net.Arathain.gatesofoblivion.entity.interphace;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface BoundEntity {
    UUID getOwnerUuid();
    void setOwnerUuid(@Nullable UUID uuid);
    void setOwner(PlayerEntity player);
    LivingEntity getOwner();
    boolean isOwner(LivingEntity entity);

    boolean isTamed();
    void setTamed(boolean tamed);

    default void onTamedChanged(){

    }

    default boolean canAttackWithOwner(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
            if (target instanceof BoundEntity) {
                BoundEntity dead = (BoundEntity)target;
                return !dead.isTamed() || dead.getOwner() != owner;
            } else if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).shouldDamagePlayer((PlayerEntity)target)) {
                return false;
            } else if (target instanceof HorseBaseEntity && ((HorseBaseEntity)target).isTame()) {
                return false;
            } else {
                return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
            }
        } else {
            return false;
        }
    }
}
