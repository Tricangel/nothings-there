package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.item.FourthDimension;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class InsanityItems {

    public static final Item DEMONITE_SHARD = registerItem("demonite_shard", Item::new, new Item.Settings());

    public static final Item DEMONITE = registerItem("demonite", Item::new, new Item.Settings());

    public static final Item WAWA = registerItem("wawa", FourthDimension::new, new Item.Settings());

    public static void init() {}

    public static Item registerItem(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NothingsThere.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

}
