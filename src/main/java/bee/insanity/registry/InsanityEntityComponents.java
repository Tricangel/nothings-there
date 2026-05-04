package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.cca.RenderAsItemComponent;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class InsanityEntityComponents implements EntityComponentInitializer {

    public static ComponentKey<RenderAsItemComponent> RENDERASITEM =
            ComponentRegistry.getOrCreate(Identifier.of(NothingsThere.MOD_ID, "renderasitem"), RenderAsItemComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerFor(Entity.class, RENDERASITEM, renderasitem -> new RenderAsItemComponent());
    }
}
