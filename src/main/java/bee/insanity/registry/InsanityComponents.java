package bee.insanity.registry;

import bee.insanity.NothingsThere;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class InsanityComponents {

    public static final ComponentType<Boolean> DEMONITE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(NothingsThere.MOD_ID, "demonite"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );

    public static void init() {}

}
