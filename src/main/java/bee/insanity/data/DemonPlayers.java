package bee.insanity.data;

import bee.insanity.NothingsThere;
import com.mojang.authlib.GameProfile;
import com.mojang.realmsclient.dto.PlayerInfo;
import com.mojang.serialization.Codec;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DemonPlayers extends SavedData {
    private final List<String> uuids;
    public static final Codec<DemonPlayers> CODEC = Codec.STRING.listOf().xmap(DemonPlayers::new, DemonPlayers::getUUIDs);
    public static final SavedDataType<DemonPlayers> TYPE = new SavedDataType<DemonPlayers>(NothingsThere.id("demon_players_saved_data"), () -> new DemonPlayers(new ArrayList<>()), CODEC, null);



    public List<String> getUUIDs() {
        return uuids;
    }

    public void addUUID(String uuid) {
        this.uuids.add(uuid);
    }

    public DemonPlayers(List<String> uuids) {
        this.uuids = uuids;
    }

    public static DemonPlayers getData(MinecraftServer server) {
        ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);

        if (level == null) {
            return new DemonPlayers(new ArrayList<>());
        }

        return level.getDataStorage().computeIfAbsent(TYPE);
    }

}
