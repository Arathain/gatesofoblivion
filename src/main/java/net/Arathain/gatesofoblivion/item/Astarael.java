package net.Arathain.gatesofoblivion.item;

import net.Arathain.gatesofoblivion.GatesOfOblivion;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class Astarael extends Item {

    public Astarael(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            Vec3d pos = user.getPos();
            ItemStack stack = user.getStackInHand(hand);
            stack.decrement(1);
            List<LivingEntity> entities = user.getEntityWorld().getEntitiesByClass(
                    LivingEntity.class,
                    new Box(
                            pos.getX() - 20, pos.getY() - 20, pos.getZ() - 20,
                            pos.getX() + 20, pos.getY() + 20, pos.getZ() + 20
                    ), (LivingEntity) -> true
            );
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_BELL_USE, SoundCategory.PLAYERS, 1, 10);

            for (LivingEntity nearbyEntity : entities) {
                nearbyEntity.kill();
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
