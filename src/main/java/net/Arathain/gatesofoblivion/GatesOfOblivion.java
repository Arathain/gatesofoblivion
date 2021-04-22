package net.Arathain.gatesofoblivion;

import net.Arathain.gatesofoblivion.entity.BoundZombie;
import net.Arathain.gatesofoblivion.entity.interphace.BoundEntity;
import net.Arathain.gatesofoblivion.item.Astarael;
import net.Arathain.gatesofoblivion.item.CodexItem;
import net.Arathain.gatesofoblivion.item.Ranna;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class GatesOfOblivion implements ModInitializer {
    public static final String MODID = "gatesofoblivion";
    private static final Map<EntityType<?>, Identifier> ENTITY_TYPES = new LinkedHashMap<>();

	public static final Item SILVER_BELL = new Item( new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item RANNA = new Ranna( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item MOSRAEL = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item KIBETH = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item DYRIM = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item BELGAER = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item SARANETH = new Item( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS));
    public static final Item ASTARAEL = new Astarael( new Item.Settings().maxCount(1).rarity(Rarity.EPIC).group(ItemGroup.TOOLS).maxDamage(0));
    public static final Item CODEX = new CodexItem( new Item.Settings().maxCount(1).rarity(Rarity.RARE).group(ItemGroup.TOOLS));
    public static final Item LOST_MEDALLION = new Item( new Item.Settings().rarity(Rarity.UNCOMMON).group(ItemGroup.MISC));
    public static final EntityType<BoundZombie> BOUND_ZOMBIE = create("bound_zombie", BoundZombie.createAttributes(), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BoundZombie::new).dimensions(EntityDimensions.fixed(0.8f, 1.8f)).build());
    private static <T extends LivingEntity> EntityType<T> create(String name, DefaultAttributeContainer.Builder attributes, EntityType<T> type) {
        FabricDefaultAttributeRegistry.register(type, attributes);
        ENTITY_TYPES.put(type, new Identifier(GatesOfOblivion.MODID, name));
        return type;
    }


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
        Registry.register(Registry.ITEM, new Identifier("gatesofoblivion", "lost_medallion"), LOST_MEDALLION);

	}
	@Override
	public void onInitialize() {
		GatesOfOblivion.register();

	}
}
