package bee.insanity.item;

import bee.insanity.registry.ModComponents;
import bee.insanity.registry.ModItems;
import bee.insanity.registry.ModTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class DemoniteRecipe extends CustomRecipe {
    public static final DemoniteRecipe INSTANCE = new DemoniteRecipe();
    public static final MapCodec<DemoniteRecipe> MAP_CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, DemoniteRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);
    public static final RecipeSerializer<DemoniteRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);


    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean bl = false;
        for (ItemStack stack : input.items()) {
            if (stack.is(ModItems.DEMONITE_SHARD)) bl = true;
        }
        return input.ingredientCount() == 2 && bl;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        ItemStack output = ItemStack.EMPTY;

        for (ItemStack stack : input.items()) {
            if (!stack.is(ModItems.DEMONITE_SHARD)) {
                output = new ItemStack(stack.typeHolder(), 1);
                output.set(ModComponents.BOOL, true);
            }
        }

        return output;
    }

    public static class DemoniteRecipeType implements RecipeType<DemoniteRecipe> {
        public static final DemoniteRecipeType INSTANCE = new DemoniteRecipeType();


    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return SERIALIZER;
    }
}
