package net.Arathain.gatesofoblivion.item;

import ladysnake.requiem.common.tag.RequiemEntityTypeTags;
import net.Arathain.gatesofoblivion.GatesOfOblivion;
import net.Arathain.gatesofoblivion.entity.interphace.BoundEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class Belgaer extends Item {

    public Belgaer(Settings settings) {
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
                user.getItemCooldownManager().set(this, 160);
                for (LivingEntity nearbyEntity : entities) {

                    if (RequiemEntityTypeTags.POSSESSABLES.contains(nearbyEntity.getType())) {
                        if ((((BoundEntity) nearbyEntity).getOwner() == user) && (user.getOffHandStack().isItemEqual(GatesOfOblivion.EMPTY_SOUL_CHAMBER.getDefaultStack()))) {
                            BlockPos entityPos = nearbyEntity.getBlockPos();
                            System.out.println(entities);
                            ItemScatterer.spawn(world, entityPos.getX(), entityPos.getY(), entityPos.getZ(), new ItemStack(GatesOfOblivion.SOUL_CHAMBER));
                            user.getOffHandStack().decrement(1);
                            nearbyEntity.remove();
                        }
                    }
                }
            }

            return TypedActionResult.success(user.getStackInHand(hand));
        }

    }
