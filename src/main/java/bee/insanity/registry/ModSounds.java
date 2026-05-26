package bee.insanity.registry;

import bee.insanity.NothingsThere;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {

    public static final SoundEvent BOMB_EXPLODE = registerSoundEvent("bomb.explode");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = NothingsThere.id(name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void init() {}

}
