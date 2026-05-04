package bee.insanity.datagen;

import bee.insanity.registry.InsanityTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class InsanityTagGen extends FabricTagProvider.ItemTagProvider {

    public InsanityTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(InsanityTags.WEAPON)
                .forceAddTag(ItemTags.SWORDS)
                .forceAddTag(ItemTags.AXES)
                .forceAddTag(ItemTags.SPEARS)
                .add(Items.MACE);

        valueLookupBuilder(InsanityTags.PROJECTILE)
                .add(Items.SNOWBALL)
                .add(Items.ARROW)
                .add(Items.SPECTRAL_ARROW)
                .add(Items.TIPPED_ARROW)
                .add(Items.TRIDENT)
                .add(Items.WIND_CHARGE);
    }
}
