package net.Arathain.gatesofoblivion.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.util.UseAction;

public class NinthCodexItem extends Item {
    public NinthCodexItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 16;
    }
}
