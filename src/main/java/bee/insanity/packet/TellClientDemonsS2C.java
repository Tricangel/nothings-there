package bee.insanity.packet;

import bee.insanity.NothingsThere;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.UUID;

public record TellClientDemonsS2C(int playerId) implements CustomPacketPayload {
    public static final Identifier TELL_CLIENT_DEMONS_PAYLOAD_ID = NothingsThere.id("tell_client_demons");
    public static final Type<TellClientDemonsS2C> TYPE = new Type<>(TELL_CLIENT_DEMONS_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, TellClientDemonsS2C> CODEC = StreamCodec.composite(ByteBufCodecs.INT, TellClientDemonsS2C::playerId, TellClientDemonsS2C::new);
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
