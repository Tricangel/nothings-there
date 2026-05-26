package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.cca.BooleanComponent;
import bee.insanity.cca.FourthDimension;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class ModEntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<BooleanComponent> IS_DEMON =
            ComponentRegistry.getOrCreate(NothingsThere.id("is_demon"), BooleanComponent.class);
    public static final ComponentKey<BooleanComponent> BOOL =
            ComponentRegistry.getOrCreate(NothingsThere.id("bool"), BooleanComponent.class);
    public static final ComponentKey<FourthDimension> IN_FOURTH_DIM =
            ComponentRegistry.getOrCreate(NothingsThere.id("in_fourth_dim"), FourthDimension.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(Player.class, IN_FOURTH_DIM, comp -> new FourthDimension());
        registry.registerFor(Player.class, IS_DEMON, comp -> new BooleanComponent("is_demon"));
        registry.registerFor(Entity.class, BOOL, comp -> new BooleanComponent("bool"));
    }

    public static void init() {}

}
