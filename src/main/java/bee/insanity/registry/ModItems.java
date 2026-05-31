package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.item.*;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Function;

public class ModItems {
    public static final Item DEMONITE_HELMET = register(
            "helmet",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterials.DEMONITE_INSTANCE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(ModArmorMaterials.DEMONITE_BASE_DURABILITY))
    );
    public static final Item DEMONITE_CHESTPLATE = register("chestplate",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterials.DEMONITE_INSTANCE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(ModArmorMaterials.DEMONITE_BASE_DURABILITY))
    );

    public static final Item DEMONITE_LEGGINGS = register(
            "leggings",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterials.DEMONITE_INSTANCE, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(ModArmorMaterials.DEMONITE_BASE_DURABILITY))
    );

    public static final Item DEMONITE_BOOTS = register(
            "boots",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterials.DEMONITE_INSTANCE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(ModArmorMaterials.DEMONITE_BASE_DURABILITY))
    );

    public static final Item DEMONITE_SHARD = register("item",
            DemoniteShard::new, new Item.Properties());

    public static final Item DEMONITE_POTION = register("bottle",
            DemonitePotion::new, new Item.Properties().component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK).usingConvertsTo(Items.GLASS_BOTTLE));


    public static final Item DEMONITE_CAGE = register("cage",
            DemoniteCage::new, new Item.Properties());


    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NothingsThere.MOD_ID, name));
        T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void init() {}

}
