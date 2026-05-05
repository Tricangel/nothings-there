package bee.insanity.registry;

import bee.insanity.NothingsThere;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {

    public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(NothingsThere.MOD_ID, "custom_category")
    );

    public static final  KeyMapping fourthDimensionKey = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                    "key.insanity.fourth_dimension",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_J,
                    CATEGORY
            ));

    public static void init() {}

}
