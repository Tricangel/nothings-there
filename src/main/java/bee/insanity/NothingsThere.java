package bee.insanity;

import bee.insanity.entity.InsanityEntitySpawns;
import bee.insanity.entity.TheWatcher;
import bee.insanity.registry.InsanityItems;
import bee.insanity.registry.InsanityMobs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NothingsThere implements ModInitializer {
	public static final String MOD_ID = "nothings-there";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		InsanityMobs.init();
		InsanityItems.init();

		FabricDefaultAttributeRegistry.register(InsanityMobs.THE_WATCHER, TheWatcher.createMobAttributes());
		InsanityEntitySpawns.addSpawns();


	}
}