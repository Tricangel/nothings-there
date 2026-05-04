package bee.insanity.registry;

import bee.insanity.NothingsThere;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class InsanityTags {

    public static final TagKey<Item> WEAPON = registerItemTag("weapon");
    public static final TagKey<Item> PROJECTILE = registerItemTag("projectile");

    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(NothingsThere.MOD_ID, name));
    }

}
