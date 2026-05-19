package bee.insanity;

import bee.insanity.cca.BooleanComponent;
import bee.insanity.packet.FourthDimensionC2SPacket;
import bee.insanity.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
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
		ModComponents.init();

		PayloadTypeRegistry.serverboundPlay().register(FourthDimensionC2SPacket.TYPE, FourthDimensionC2SPacket.CODEC);


		ServerPlayNetworking.registerGlobalReceiver(FourthDimensionC2SPacket.TYPE, (packet, context) -> {
			Entity entity = context.player().level().getEntity(packet.id());

			if (entity instanceof Player player) {
				BooleanComponent comp = ModEntityComponents.IN_FOURTH_DIM.get(player);
				comp.setBool(!comp.getBool());
				ModEntityComponents.IN_FOURTH_DIM.sync(player);
				player.sendOverlayMessage(Component.literal(String.valueOf(ModEntityComponents.IN_FOURTH_DIM.get(player).getBool())));
			}

		});
	}

	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}

}