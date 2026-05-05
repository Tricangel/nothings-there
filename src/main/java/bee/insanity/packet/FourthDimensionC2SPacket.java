package bee.insanity.packet;

import bee.insanity.NothingsThere;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record FourthDimensionC2SPacket(int id) implements CustomPacketPayload {
    public static final Identifier FOURTH_DIMENSION_PAYLOAD_ID = NothingsThere.id("fourth_dimension");
    public static final Type<FourthDimensionC2SPacket> TYPE = new Type<>(FOURTH_DIMENSION_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, FourthDimensionC2SPacket> CODEC = StreamCodec.composite(ByteBufCodecs.INT, FourthDimensionC2SPacket::id, FourthDimensionC2SPacket::new);
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
