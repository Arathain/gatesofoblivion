package net.Arathain.gatesofoblivion.item;


import ladysnake.requiem.common.tag.RequiemEntityTypeTags;
import net.Arathain.gatesofoblivion.GatesOfOblivion;
import net.Arathain.gatesofoblivion.entity.interphace.BoundEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;


public class Saraneth extends Item {
    public Saraneth(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && !user.getItemCooldownManager().isCoolingDown(GatesOfOblivion.RANNA)) {
            Vec3d pos = user.getPos();
            ItemStack stack = user.getStackInHand(hand);
            List<ZombieEntity> entities = user.getEntityWorld().getEntitiesByClass(
                    ZombieEntity.class,
                    new Box(
                            pos.getX() - 20, pos.getY() - 20, pos.getZ() - 20,
                            pos.getX() + 20, pos.getY() + 20, pos.getZ() + 20
                    ), (ZombieEntity) -> true
            );
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_BELL_USE, SoundCategory.PLAYERS, 100000, 10);
            user.getItemCooldownManager().set(this, 480);
            for (ZombieEntity nearbyEntity : entities) {
                System.out.println(entities);
                if (RequiemEntityTypeTags.POSSESSABLES.contains(nearbyEntity.getType())) {
                    Random random = new Random();
                    int bound = 101;
                    if (random.nextInt(bound) >= 20) {
                        nearbyEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.UNLUCK, 20, 0));
                    }
                }
            }
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }

}
