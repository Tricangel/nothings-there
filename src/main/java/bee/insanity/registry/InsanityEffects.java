package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.effect.DemoniteEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class InsanityEffects {

    public static final RegistryEntry<StatusEffect> DEMONITE = registerStatusEffect("demonite", new DemoniteEffect(StatusEffectCategory.BENEFICIAL, 12));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(NothingsThere.MOD_ID, name), statusEffect);
    }

    public static void init() {}

}
