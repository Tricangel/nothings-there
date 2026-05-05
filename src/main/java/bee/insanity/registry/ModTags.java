package bee.insanity.registry;

import bee.insanity.NothingsThere;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static final TagKey<Item> REPAIRS_DEMONITE_ARMOR = new TagKey<Item>(Registries.ITEM, Identifier.fromNamespaceAndPath(NothingsThere.MOD_ID, "repairs_demonite_armor"));

    public static void init() {}
}
