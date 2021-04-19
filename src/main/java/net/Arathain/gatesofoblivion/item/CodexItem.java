package net.Arathain.gatesofoblivion.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CodexItem extends Item {

    public CodexItem(Settings settings) {
        super(settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state)
    {
        return 100;
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner)
    {
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
            if (!world.isClient) {
                FireballEntity fireballEntity = new FireballEntity(EntityType.FIREBALL, world);
                fireballEntity.setItem(new ItemStack(Items.FIRE_CHARGE));
                fireballEntity.setOwner(user);
                fireballEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
                fireballEntity.updatePosition(user.getX() - 1, user.getEyeY() + user.getRandom().nextGaussian(), user.getZ() + user.getRandom().nextGaussian());
                fireballEntity.addVelocity(user.getRandom().nextDouble()/10, 0, user.getRandom().nextGaussian()/10);
                world.spawnEntity(fireballEntity);
            }

            return TypedActionResult.success(user.getStackInHand(hand));




    }

}
