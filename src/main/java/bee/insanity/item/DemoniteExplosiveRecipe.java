package bee.insanity.item;

import bee.insanity.registry.ModBlockProperties;
import bee.insanity.registry.ModBlocks;
import bee.insanity.registry.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class DemoniteExplosiveRecipe extends CustomRecipe {
    public static final DemoniteExplosiveRecipe INSTANCE = new DemoniteExplosiveRecipe();
    public static final MapCodec<DemoniteExplosiveRecipe> MAP_CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, DemoniteExplosiveRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);
    public static final RecipeSerializer<DemoniteExplosiveRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);


    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean bl = false;
        boolean bl2 = false;
        for (ItemStack stack : input.items()) {
            if (stack.is(Items.TNT)) bl = true;
            if (stack.is(ModItems.DEMONITE_SHARD)) bl2 = true;
        }
        return input.ingredientCount() > 1 && bl && bl2;
    }

    @Override
    public @NonNull ItemStack assemble(CraftingInput input) {
        float blastResistance = 0;
        int shards = 0;

        for (ItemStack stack : input.items()) {
            if (stack.getItem() instanceof BlockItem item) {
                blastResistance += item.getBlock().getExplosionResistance();
            }
            if (stack.is(ModItems.DEMONITE_SHARD)) {
                shards++;
            }

        }

        BlockState state = ModBlocks.DEMONITE_EXPLOSIVE.defaultBlockState();
        if (blastResistance > 20) blastResistance = 20;
        state.setValue(ModBlockProperties.EXPLOSIVE_RESISTANCE, (int) blastResistance);
        state.setValue(ModBlockProperties.SHARD_AMOUNT, shards);
        ItemStack output = new ItemStack(ModBlocks.DEMONITE_EXPLOSIVE);
        output.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY.with(ModBlockProperties.EXPLOSIVE_RESISTANCE, state));
        output.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY.with(ModBlockProperties.SHARD_AMOUNT, state));


        return output;
    }

    public static class DemoniteRecipeType implements RecipeType<DemoniteExplosiveRecipe> {
        public static final DemoniteRecipeType INSTANCE = new DemoniteRecipeType();


    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return SERIALIZER;
    }
}
