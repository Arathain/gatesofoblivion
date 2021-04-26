package net.Arathain.gatesofoblivion.item;

import ladysnake.requiem.api.v1.remnant.RemnantComponent;
import ladysnake.requiem.api.v1.remnant.RemnantType;
import ladysnake.requiem.common.RequiemRegistries;
import ladysnake.requiem.common.remnant.RemnantTypes;
import ladysnake.requiem.common.remnant.SimpleRemnantType;
import ladysnake.requiem.common.tag.RequiemEntityTypeTags;
import net.Arathain.gatesofoblivion.GatesOfOblivion;
import net.Arathain.gatesofoblivion.entity.interphace.BoundEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.EntityTypeTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class Ranna extends Item {
    public Ranna(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && !user.getItemCooldownManager().isCoolingDown(GatesOfOblivion.RANNA)) {
            Vec3d pos = user.getPos();
            ItemStack stack = user.getStackInHand(hand);
            List<LivingEntity> entities = user.getEntityWorld().getEntitiesByClass(
                    LivingEntity.class,
                    new Box(
                            pos.getX() - 20, pos.getY() - 20, pos.getZ() - 20,
                            pos.getX() + 20, pos.getY() + 20, pos.getZ() + 20
                    ), (LivingEntity) -> true
            );
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_BELL_USE, SoundCategory.PLAYERS, 100000, 10);
            user.getItemCooldownManager().set(this, 480);
            for (LivingEntity nearbyEntity : entities) {
                System.out.println(entities);
                if (RequiemEntityTypeTags.POSSESSABLES.contains(nearbyEntity.getType())) {
                    Random random = new Random();
                    int bound = 101;
                    if (random.nextInt(bound) >= 20 && (nearbyEntity instanceof BoundEntity)) {
                        if (!((BoundEntity) nearbyEntity).isOwner(user)) {
                            nearbyEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 1200, 10));
                            nearbyEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 2));
                        }
                    }


                }
            }

        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
