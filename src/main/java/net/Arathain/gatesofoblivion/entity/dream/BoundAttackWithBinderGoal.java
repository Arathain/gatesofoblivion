package net.Arathain.gatesofoblivion.entity.dream;

import net.Arathain.gatesofoblivion.entity.interphace.BoundEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;

import java.util.EnumSet;

public class BoundAttackWithBinderGoal<T extends BoundEntity> extends TrackTargetGoal {
    private final T dead;
    private LivingEntity attacking;
    private int lastAttackTime;

    public BoundAttackWithBinderGoal(T dead) {
        super((MobEntity) dead, false);
        this.dead = dead;
        this.setControls(EnumSet.of(Control.TARGET));
    }

    public boolean canStart() {
        if (this.dead.isTamed()) {
            LivingEntity livingEntity = this.dead.getOwner();
            if (livingEntity == null) {
                return false;
            } else {
                this.attacking = livingEntity.getAttacking();
                int i = livingEntity.getLastAttackTime();
                return i != this.lastAttackTime && this.canTrack(this.attacking, TargetPredicate.DEFAULT) && this.dead.canAttackWithOwner(this.attacking, livingEntity);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.attacking);
        LivingEntity livingEntity = this.dead.getOwner();
        if (livingEntity != null) {
            this.lastAttackTime = livingEntity.getLastAttackTime();
        }

        super.start();
    }
}
