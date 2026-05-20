package bee.insanity;

import bee.insanity.cca.BooleanComponent;
import bee.insanity.cca.FourthDimension;
import bee.insanity.entity.client.TheWatcherRenderer;
import bee.insanity.packet.FourthDimensionC2SPacket;
import bee.insanity.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;

public class NothingsThereClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ModEntityModelLayers.registerModelLayers();
        EntityRenderers.register(ModEntityTypes.THE_WATCHER, TheWatcherRenderer::new);


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                LocalPlayer player = client.player;
                while (ModKeybinds.fourthDimensionKey.consumeClick()) {
                    FourthDimension comp = ModEntityComponents.IN_FOURTH_DIM.get(player);
                    if (comp.getTimeIn4thDim() > 0) {
                        comp.setTimeIn4thDim(0);
                    }
                    if (comp.getTimeIn4thDim() == 0) {
                        comp.setTimeIn4thDim(100);
                        FourthDimensionC2SPacket packet = new FourthDimensionC2SPacket(player.getId());
                        ClientPlayNetworking.send(packet);
                    }

                }

                while (ModKeybinds.becomeEvil.consumeClick()) {
                    BooleanComponent comp = ModEntityComponents.IS_DEMON.get(player);
                    comp.setBool(!comp.getBool());
                }
            }
        });

        ItemTooltipCallback.EVENT.register(((stack, tooltipContext, tooltipFlag, lines) -> {
            if (stack.has(ModComponents.STACK)) {
                lines.add(Component.literal(stack.get(ModComponents.STACK).toString()));
            }
        }));

    }
}
