package bee.insanity.mixin.client;

import bee.insanity.NothingsThere;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;

@Mixin(ClientSuggestionProvider.class)
public class ClientSuggestionProviderMixin {

    @ModifyExpressionValue(method = "getOnlinePlayerNames",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;getOnlinePlayers()Ljava/util/Collection;"))
    private Collection<PlayerInfo> insanity$getOnlinePlayerNames(Collection<PlayerInfo> original) {

        for (PlayerInfo playerInfo : original) {
            for (Player player : NothingsThere.players) {
                if (playerInfo.getProfile().equals(player.getGameProfile())) {
                    original.remove(playerInfo);
                }
            }
        }
        return original;
    }

}
