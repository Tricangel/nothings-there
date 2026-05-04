package bee.insanity.item.recipe;

import bee.insanity.registry.InsanityComponents;
import bee.insanity.registry.InsanityItems;
import bee.insanity.registry.InsanityTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.world.World;

public class DemoniteRecipe extends SpecialCraftingRecipe {
    private static final Ingredient DEMONITE = Ingredient.ofItem(InsanityItems.DEMONITE_SHARD);
    public DemoniteRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        int count = 0;
        boolean food = false;
        boolean modifier = false;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (!food && (stack.getComponents().contains(DataComponentTypes.FOOD) || stack.isIn(InsanityTags.PROJECTILE) || stack.isIn(InsanityTags.WEAPON))) {
                    food = true;
                } else {
                    if (!modifier && DEMONITE.test(stack)) {
                        modifier = true;
                    } else return false;
                }
                count++;
            }
        }
        return food && modifier && count == 2;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        ItemStack output = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStackInSlot(i);

            if (!stack.isEmpty() && (stack.getComponents().contains(DataComponentTypes.FOOD) || stack.isIn(InsanityTags.PROJECTILE) || stack.isIn(InsanityTags.WEAPON))) {
                output = stack.copyWithCount(1);
                break;
            }

        }

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (!stack.isEmpty() && DEMONITE.test(stack)) {
                output.set(InsanityComponents.DEMONITE, true);
                break;
            }

        }



        return output.copyWithCount(1);
    }

    public static class DemoniteRecipeType implements RecipeType<DemoniteRecipe> {
        public static final DemoniteRecipeType INSTANCE = new DemoniteRecipeType();

        private DemoniteRecipeType() {

        }
    }

    @Override
    public RecipeSerializer<? extends SpecialCraftingRecipe> getSerializer() {
        return new SpecialCraftingRecipe.SpecialRecipeSerializer<DemoniteRecipe>(DemoniteRecipe::new);
    }
}
