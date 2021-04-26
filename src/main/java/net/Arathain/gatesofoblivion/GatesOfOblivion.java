package net.Arathain.gatesofoblivion;

import net.Arathain.gatesofoblivion.item.Astarael;
import net.Arathain.gatesofoblivion.item.CodexItem;
import net.Arathain.gatesofoblivion.item.Ranna;
import net.Arathain.gatesofoblivion.item.Saraneth;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;


public class GatesOfOblivion implements ModInitializer {

	public static final Item SILVER_BELL = new Item( new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item RANNA = new Ranna( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item MOSRAEL = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item KIBETH = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item DYRIM = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item BELGAER = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item SARANETH = new Saraneth( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item ASTARAEL = new Astarael( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS).maxDamage(0));
    public static final Item CODEX = new CodexItem( new Item.Settings().maxCount(1).rarity(Rarity.RARE).group(ItemGroup.TOOLS));
    public static final Item LOST_MEDALLION = new Item( new Item.Settings().rarity(Rarity.UNCOMMON).group(ItemGroup.MISC));
    public static final Item BLACK_ICE = new Item( new Item.Settings().rarity(Rarity.UNCOMMON).group(ItemGroup.MISC));
    public static final Item FREE_MAGIC_CREST = new Item( new Item.Settings().rarity(Rarity.UNCOMMON).group(ItemGroup.MISC));
    public static final Item WRAITHCLOAK = new Item( new Item.Settings().rarity(Rarity.RARE).group(ItemGroup.MISC));
    public static final Item NINTH_BRIGHT_SHINER_CODEX = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));


    public static void register() {
		Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "silver_bell"), SILVER_BELL);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "ranna"), RANNA);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "mosrael"), MOSRAEL);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "kibeth"), KIBETH);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "dyrim"), DYRIM);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "belgaer"), BELGAER);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "saraneth"), SARANETH);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "astarael"), ASTARAEL);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "codex"), CODEX);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "ninth_codex"), NINTH_BRIGHT_SHINER_CODEX);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "lost_medallion"), LOST_MEDALLION);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "black_ice"), BLACK_ICE);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "wraithcloak"), WRAITHCLOAK);
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "free_magic_crest"), FREE_MAGIC_CREST);

	}
	@Override
	public void onInitialize() {
		GatesOfOblivion.register();

	}
}
