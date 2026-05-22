package bee.insanity.mixin;

import bee.insanity.data.DemonPlayers;
import bee.insanity.packet.TellClientDemonsS2C;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.players.PlayerList;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerList.class)
public abstract class PlayerListMixin {


    @Shadow
    @Final
    private MinecraftServer server;

    @Shadow
    public abstract @Nullable ServerPlayer getPlayer(UUID uuid);

    @Inject(method = "placeNewPlayer", at = @At("HEAD"))
    private void tellDemons(Connection connection, ServerPlayer player, CommonListenerCookie cookie, CallbackInfo ci) {

        DemonPlayers.getData(server).getUUIDs().forEach(uuid -> {
            int id = this.getPlayer(UUID.fromString(uuid)).getId();

            TellClientDemonsS2C packet = new TellClientDemonsS2C(id);
            ServerPlayNetworking.send(player, packet);
        });


    }

}
