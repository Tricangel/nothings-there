package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.cca.BooleanComponent;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class ModEntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<BooleanComponent> IN_FOURTH_DIM =
            ComponentRegistry.getOrCreate(NothingsThere.id("in_fourth_dim"), BooleanComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {

        registry.registerFor(Player.class, IN_FOURTH_DIM, comp -> new BooleanComponent("in_fourth_dim"));
    }

    public static void init() {}

}
