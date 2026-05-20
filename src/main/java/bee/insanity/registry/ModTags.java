package bee.insanity.registry;

import bee.insanity.NothingsThere;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static final TagKey<Item> REPAIRS_DEMONITE_ARMOR = new TagKey<Item>(Registries.ITEM, NothingsThere.id("repairs_demonite_armor"));
    public static final TagKey<Item> DEMONITE_COMBINABLE = new TagKey<Item>(Registries.ITEM, NothingsThere.id("demonite_combinable"));

    public static void init() {}
}
