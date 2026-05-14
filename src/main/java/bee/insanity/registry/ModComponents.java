package bee.insanity.registry;

import bee.insanity.NothingsThere;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class ModComponents {

    public static void init() {}

    public static final DataComponentType<ItemStack> STACK = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            NothingsThere.id("stack"),
            DataComponentType.<ItemStack>builder().persistent(ItemStack.CODEC).build()
    );

}
