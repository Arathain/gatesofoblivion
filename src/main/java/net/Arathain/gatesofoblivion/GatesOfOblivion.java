package net.Arathain.gatesofoblivion;

import com.ibm.icu.impl.CalendarCache;
import net.Arathain.gatesofoblivion.entity.MordicantEntity;
import net.Arathain.gatesofoblivion.item.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;


public class GatesOfOblivion implements ModInitializer {

    public static final String MODID = "gatesofoblivion";

	public static final Item SILVER_BELL = new Item( new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item RANNA = new Ranna( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item MOSRAEL = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item KIBETH = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item DYRIM = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item BELGAER = new Belgaer( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item SARANETH = new Saraneth( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item ASTARAEL = new Astarael( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS).maxDamage(0));
    public static final Item CODEX = new CodexItem( new Item.Settings().maxCount(1).rarity(Rarity.RARE).group(ItemGroup.TOOLS));
    public static final Item LOST_MEDALLION = new Item( new Item.Settings().rarity(Rarity.UNCOMMON).group(ItemGroup.MISC));
    public static final Item BLACK_ICE = new Item( new Item.Settings().rarity(Rarity.UNCOMMON).group(ItemGroup.MISC));
    public static final Item FREE_MAGIC_CREST = new Item( new Item.Settings().rarity(Rarity.UNCOMMON).group(ItemGroup.MISC));
    public static final Item WRAITHCLOAK = new Item( new Item.Settings().rarity(Rarity.RARE).group(ItemGroup.MISC));
    public static final Item NINTH_BRIGHT_SHINER_CODEX = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item EMPTY_SOUL_CHAMBER = new Item( new Item.Settings().rarity(Rarity.UNCOMMON).group(ItemGroup.MISC));
    public static final Item SOUL_CHAMBER = new Item( new Item.Settings().rarity(Rarity.RARE).group(ItemGroup.MISC));
    public static final EntityType<MordicantEntity> MORDICANT = createEntity("mordicant", MordicantEntity.createAttributes(), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MordicantEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.9f)).fireImmune().build());


    public static void register() {
		Registry.register(Registry.ITEM, new Identifier(MODID, "silver_bell"), SILVER_BELL);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ranna"), RANNA);
        Registry.register(Registry.ITEM, new Identifier(MODID, "mosrael"), MOSRAEL);
        Registry.register(Registry.ITEM, new Identifier(MODID, "kibeth"), KIBETH);
        Registry.register(Registry.ITEM, new Identifier(MODID, "dyrim"), DYRIM);
        Registry.register(Registry.ITEM, new Identifier(MODID, "belgaer"), BELGAER);
        Registry.register(Registry.ITEM, new Identifier(MODID, "saraneth"), SARANETH);
        Registry.register(Registry.ITEM, new Identifier(MODID, "astarael"), ASTARAEL);
        Registry.register(Registry.ITEM, new Identifier(MODID, "codex"), CODEX);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ninth_codex"), NINTH_BRIGHT_SHINER_CODEX);
        Registry.register(Registry.ITEM, new Identifier(MODID, "lost_medallion"), LOST_MEDALLION);
        Registry.register(Registry.ITEM, new Identifier(MODID, "black_ice"), BLACK_ICE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "wraithcloak"), WRAITHCLOAK);
        Registry.register(Registry.ITEM, new Identifier(MODID, "free_magic_crest"), FREE_MAGIC_CREST);
        Registry.register(Registry.ITEM, new Identifier(MODID, "filled_soul_chamber"), SOUL_CHAMBER);
        Registry.register(Registry.ITEM, new Identifier(MODID, "soul_chamber"), EMPTY_SOUL_CHAMBER);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(MODID, "mordicant"), MORDICANT);

	}
    private static <T extends LivingEntity> EntityType<T> createEntity(String name, DefaultAttributeContainer.Builder attributes, EntityType<T> type) {
        FabricDefaultAttributeRegistry.register(type, attributes);

        return type;
    }

    private static <T extends Entity> EntityType<T> createEntity(String name, EntityType<T> type) {

        return type;
    }
	@Override
	public void onInitialize() {
		GatesOfOblivion.register();

	}
    public static Identifier ID(String path)
    {
        return new Identifier(MODID, path);
    }
}
