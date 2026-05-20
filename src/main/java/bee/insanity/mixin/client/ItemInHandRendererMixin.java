package bee.insanity.mixin.client;

import bee.insanity.item.DemoniteShard;
import bee.insanity.registry.ModComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @ModifyVariable(at = @At(value = "HEAD"), method = "renderItem", argsOnly = true)
    private ItemStack wawa(ItemStack original) {
        if (original.getItem() instanceof DemoniteShard && Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            List<Item> stacks = new ArrayList<>(player.registryAccess().lookupOrThrow(Registries.ITEM).stream().toList());
            stacks.remove(Items.AIR);
            ItemStack newStack = stacks.get(new Random().nextInt(stacks.size())).getDefaultInstance();

            return original.getOrDefault(ModComponents.STACK, newStack);
        }

        return original;
    }

}
