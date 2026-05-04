package bee.insanity.datagen;

import bee.insanity.registry.InsanityBlocks;
import bee.insanity.registry.InsanityItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class InsanityModelGen extends FabricModelProvider {
    public InsanityModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(InsanityBlocks.DEMONITE_PLATING);
        blockStateModelGenerator.registerSimpleCubeAll(InsanityBlocks.DEMONITE_BLOCK);
        blockStateModelGenerator.registerAmethyst(InsanityBlocks.BLEED_AMETHYST_CLUSTER);
        blockStateModelGenerator.registerAmethyst(InsanityBlocks.SMALL_BLEED_AMETHYST_BUD);
        blockStateModelGenerator.registerAmethyst(InsanityBlocks.MEDIUM_BLEED_AMETHYST_BUD);
        blockStateModelGenerator.registerAmethyst(InsanityBlocks.LARGE_BLEED_AMETHYST_BUD);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(InsanityItems.DEMONITE_SHARD, Models.GENERATED);
    }
}
