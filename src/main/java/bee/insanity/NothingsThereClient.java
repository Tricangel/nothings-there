package bee.insanity;

import bee.insanity.packet.FourthDimensionC2SPacket;
import bee.insanity.registry.ModKeybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.player.LocalPlayer;

public class NothingsThereClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                LocalPlayer player = client.player;
                while (ModKeybinds.fourthDimensionKey.consumeClick()) {
                    FourthDimensionC2SPacket packet = new FourthDimensionC2SPacket(player.getId());
                    ClientPlayNetworking.send(packet);
                }
            }
        });
    }
}
