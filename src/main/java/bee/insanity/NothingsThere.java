package bee.insanity;

import bee.insanity.entity.InsanityEntitySpawns;
import bee.insanity.entity.TheWatcher;
import bee.insanity.item.recipe.DemoniteRecipe;
import bee.insanity.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NothingsThere implements ModInitializer {
	public static final String MOD_ID = "nothings-there";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		InsanityMobs.init();
		InsanityItems.init();
		InsanityBlocks.init();
		InsanityEffects.init();
		InsanityComponents.init();
		InsanitySounds.init();

		Registry.register(Registries.RECIPE_TYPE, Identifier.of(MOD_ID, "demonite_crafting"), DemoniteRecipe.DemoniteRecipeType.INSTANCE);
		Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MOD_ID, "demonite_crafting"), new SpecialCraftingRecipe.SpecialRecipeSerializer<DemoniteRecipe>(DemoniteRecipe::new));

		FabricDefaultAttributeRegistry.register(InsanityMobs.THE_WATCHER, TheWatcher.createMobAttributes());
		InsanityEntitySpawns.addSpawns();

	}


	public static boolean getDemoniteNearby(BlockPos pos, World world) {

		for (int n = -4; n < 6; n++) {
			for (int e = -4; e < 6; e++) {
				for (int u = -4; u < 6; u++) {
					if (world.getBlockState(pos.up(u).north(n).east(e)).getBlock().equals(InsanityBlocks.DEMONITE_BLOCK)) {
						return true;
					}
				}
			}
		}
		return false;

	}

}