package bee.insanity.registry;

import bee.insanity.NothingsThere;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;

public class ModComponents {

    public static void init() {}

    public static final DataComponentType<ItemStack> STACK = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            NothingsThere.id("stack"),
            DataComponentType.<ItemStack>builder().persistent(ItemStack.CODEC).build()
    );

    public static final DataComponentType<Boolean> BOOL = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            NothingsThere.id("bool"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
    );




}
