package net.Arathain.gatesofoblivion.item;

import net.Arathain.gatesofoblivion.GatesOfOblivion;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.*;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
public class CodexItem extends Item {

    public CodexItem(Settings settings) {
        super(settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 100;
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (user.getItemCooldownManager().isCoolingDown(GatesOfOblivion.CODEX)) {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }
        else {
            ItemStack itemStack = user.getOffHandStack();
            if (user.getOffHandStack().isItemEqual(Items.BLAZE_ROD.getDefaultStack())) {
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
                if (!world.isClient) {
                    FireballEntity fireballEntity = new FireballEntity(EntityType.FIREBALL, world);
                    fireballEntity.setItem(new ItemStack(Items.FIRE_CHARGE));
                    fireballEntity.setOwner(user);
                    fireballEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 4.0F, 0.0F);
                    fireballEntity.updatePosition(user.getX() + 1, user.getEyeY() + user.getRandom().nextGaussian(), user.getZ() + user.getRandom().nextGaussian());
                    world.spawnEntity(fireballEntity);
                    user.addExhaustion(16f);
                    user.getItemCooldownManager().set(this, 10);
                    Random random = new Random();
                    int bound = 101;
                    if ((random.nextInt(bound) <= 4) && !(user.isCreative())) {
                        itemStack.decrement(1);
                    }
                }
                return TypedActionResult.success(user.getStackInHand(hand));
            }
            if (user.getOffHandStack().isItemEqual(Items.SHULKER_SHELL.getDefaultStack())) {
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SHULKER_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
                if (!world.isClient) {
                    ShulkerBulletEntity bulletEntity = new ShulkerBulletEntity(EntityType.SHULKER_BULLET, world);
                    bulletEntity.setOwner(user);
                    bulletEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
                    bulletEntity.updatePosition(user.getX(), user.getEyeY(), user.getZ());
                    bulletEntity.addVelocity(user.getRandom().nextDouble() / 10, 0, user.getRandom().nextGaussian() / 10);
                    world.spawnEntity(bulletEntity);
                    user.addExhaustion(8f);
                    user.getItemCooldownManager().set(this, 10);
                    Random random = new Random();
                    int bound = 101;
                    if ((random.nextInt(bound) <= 4) && !(user.isCreative())) {
                        itemStack.decrement(1);
                    }
                }
                return TypedActionResult.success(user.getStackInHand(hand));
            }
            if (user.getOffHandStack().isItemEqual(Items.HEART_OF_THE_SEA.getDefaultStack())) {
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
                if (!world.isClient) {
                    Objects.requireNonNull(Objects.requireNonNull(world.getServer()).getWorld(World.OVERWORLD)).setWeather(0, 10000, true, true);

                    user.addExhaustion(160f);
                    user.getItemCooldownManager().set(this, 1280);
                }
                return TypedActionResult.success(user.getStackInHand(hand));
            }
            else {
                return TypedActionResult.fail(user.getStackInHand(hand));
            }
        }
    }

}




