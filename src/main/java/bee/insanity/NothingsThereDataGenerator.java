package bee.insanity;

import bee.insanity.datagen.InsanityModelGen;
import bee.insanity.datagen.InsanityTagGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class NothingsThereDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(InsanityModelGen::new);
		pack.addProvider(InsanityTagGen::new);

	}
}
