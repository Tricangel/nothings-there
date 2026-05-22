package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.item.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Function;

public class ModItems {
    public static final Item DEMONITE_HELMET = register(
            "demonite_helmet",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterials.DEMONITE_INSTANCE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(ModArmorMaterials.DEMONITE_BASE_DURABILITY))
    );
    public static final Item DEMONITE_CHESTPLATE = register("demonite_chestplate",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterials.DEMONITE_INSTANCE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(ModArmorMaterials.DEMONITE_BASE_DURABILITY))
    );

    public static final Item DEMONITE_LEGGINGS = register(
            "demonite_leggings",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterials.DEMONITE_INSTANCE, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(ModArmorMaterials.DEMONITE_BASE_DURABILITY))
    );

    public static final Item DEMONITE_BOOTS = register(
            "demonite_boots",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterials.DEMONITE_INSTANCE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(ModArmorMaterials.DEMONITE_BASE_DURABILITY))
    );

    public static final Item DEMONITE_SHARD = register("demonite_shard",
            DemoniteShard::new, new Item.Properties());

    public static final Item DEMONITE_POTION = register("demonite_potion",
            DemonitePotion::new, new Item.Properties());

    public static final Item DEMONITE_SPLASH_POTION = register("demonite_splash_potion",
            DemoniteSplashPotion::new, new Item.Properties());

    public static final Item DEMONITE_CAGE = register("demonite_cage",
            DemoniteCage::new, new Item.Properties());

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NothingsThere.MOD_ID, name));
        T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void init() {}

}
