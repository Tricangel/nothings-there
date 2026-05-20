package bee.insanity;

import bee.insanity.item.DemoniteRecipe;
import bee.insanity.packet.FourthDimensionC2SPacket;
import bee.insanity.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NothingsThere implements ModInitializer {
	public static final String MOD_ID = "insanity";
	public static List<Player> players = new ArrayList<>();
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.init();
		ModKeybinds.init();
		ModTags.init();
		ModEntityComponents.init();
		ModEntityTypes.init();
		ModEntityTypes.registerAttributes();
		ModEntitySpawns.addSpawns();
		ModComponents.init();

		Registry.register(BuiltInRegistries.RECIPE_TYPE, id("demonite_crafting"), DemoniteRecipe.DemoniteRecipeType.INSTANCE);
		Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id("demonite_crafting"), DemoniteRecipe.SERIALIZER);


		PayloadTypeRegistry.serverboundPlay().register(FourthDimensionC2SPacket.TYPE, FourthDimensionC2SPacket.CODEC);


		ServerPlayNetworking.registerGlobalReceiver(FourthDimensionC2SPacket.TYPE, (packet, context) -> {


		});
	}

	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}

}