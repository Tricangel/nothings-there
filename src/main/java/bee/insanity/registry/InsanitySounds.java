package bee.insanity.registry;

import bee.insanity.NothingsThere;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class InsanitySounds {
    public static final SoundEvent BOMB_TICK = registerSoundEvent("bomb.tick");
    public static final SoundEvent BOMB_EXPLODE = registerSoundEvent("bomb.explode");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(NothingsThere.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void init() {}
}
