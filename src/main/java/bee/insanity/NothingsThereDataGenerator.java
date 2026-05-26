package bee.insanity;

import bee.insanity.datagen.ModModelGen;
import bee.insanity.datagen.ModTagGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class NothingsThereDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModTagGen::new);
		pack.addProvider(ModModelGen::new);

	}
}
