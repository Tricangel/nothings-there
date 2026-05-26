package bee.insanity.compat;

import bee.insanity.NothingsThere;
import bee.insanity.registry.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return NothingsThere.id("jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<Item> items = List.of(ModItems.DEMONITE_SHARD, ModItems.DEMONITE_BOOTS);
        registration.getIngredientManager().removeIngredientsAtRuntime(registration.getIngredientManager().getIngredientType(ModItems.DEMONITE_BOOTS), items);
        IModPlugin.super.registerRecipes(registration);
    }
}
