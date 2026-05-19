package bee.insanity.mixin;

import bee.insanity.NothingsThere;
import bee.insanity.registry.ModItems;
import bee.insanity.registry.ModTags;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;
import java.util.HashSet;

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
