package bee.insanity;

import bee.insanity.item.DemoniteRecipe;
import bee.insanity.packet.FourthDimensionC2SPacket;
import bee.insanity.packet.TellClientDemonsS2C;
import bee.insanity.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.ItemEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.HitResult;
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
		FabricPotionBrewingBuilder.BUILD.register(builder -> {
			builder.addContainer(ModItems.DEMONITE_POTION);
			builder.addContainer(ModItems.DEMONITE_SPLASH_POTION);
			builder.addContainerRecipe(Items.POTION, ModItems.DEMONITE_SHARD, ModItems.DEMONITE_POTION);
			builder.addContainerRecipe(Items.SPLASH_POTION, ModItems.DEMONITE_SHARD, ModItems.DEMONITE_SPLASH_POTION);
		});

		ModItems.init();
		ModKeybinds.init();
		ModTags.init();
		ModEntityComponents.init();
		ModEntityTypes.init();
		ModEntityTypes.registerAttributes();
		ModEntitySpawns.addSpawns();
		ModComponents.init();
		ModBlocks.init();
		ModSounds.init();

		Registry.register(BuiltInRegistries.RECIPE_TYPE, id("demonite_crafting"), DemoniteRecipe.DemoniteRecipeType.INSTANCE);
		Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id("demonite_crafting"), DemoniteRecipe.SERIALIZER);


		PayloadTypeRegistry.serverboundPlay().register(FourthDimensionC2SPacket.TYPE, FourthDimensionC2SPacket.CODEC);
		PayloadTypeRegistry.clientboundPlay().register(TellClientDemonsS2C.TYPE, TellClientDemonsS2C.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(FourthDimensionC2SPacket.TYPE, (packet, context) -> {


		});

		DispenserBlock.registerBehavior(ModBlocks.ANGRY_AIR.asItem(), ((source, dispensed) -> {
			Direction direction = source.state().getValue(BlockStateProperties.FACING);
			Level level = source.level();
		if (level.getBlockState(source.pos().relative(direction)).isAir()) {
			level.setBlockAndUpdate(source.pos().relative(direction), ModBlocks.ANGRY_AIR.defaultBlockState());
			dispensed.shrink(1);
		}

            return dispensed;
        }));

	}



	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}

}