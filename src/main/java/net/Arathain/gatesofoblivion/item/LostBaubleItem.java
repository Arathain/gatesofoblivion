package net.Arathain.gatesofoblivion.item;


import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Iterator;


public class LostBaubleItem extends Item {

    public LostBaubleItem(Settings settings) {
        super(settings);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClient) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            BlockPos blockPos = player.getBlockPos();
            float f = (float)Math.min(16, 2 + 20);
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            BlockState blockState = Blocks.SOUL_FIRE.getDefaultState();
            Iterator var7 = BlockPos.iterate(blockPos.add((double)(-f), 1.0D, (double)(-f)), blockPos.add((double)f, -1.0D, (double)f)).iterator();

            while(var7.hasNext()) {
                BlockPos blockPos2 = (BlockPos)var7.next();
                if (blockPos2.isWithinDistance(player.getPos(), (double)f)) {
                    mutable.set(blockPos2.getX(), blockPos2.getY() + 2, blockPos2.getZ());
                    BlockState blockState2 = world.getBlockState(mutable);
                    if (blockState2.isAir()) {
                        BlockState blockState3 = world.getBlockState(blockPos2);
                        if (blockState3.getMaterial() == Material.FIRE && (Integer)blockState3.get(FluidBlock.LEVEL) == 0 && blockState.canPlaceAt(world, blockPos2) && world.canPlace(blockState, blockPos2, ShapeContext.absent())) {
                            world.setBlockState(blockPos2, blockState);
                            world.getBlockTickScheduler().schedule(blockPos2, Blocks.SOUL_FIRE, MathHelper.nextInt(player.getRandom(), 120, 240));
                        }
                    }
                }
            }

        }
    }

}




