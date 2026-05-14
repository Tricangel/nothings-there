package bee.insanity.mixin.client;

import bee.insanity.entity.client.FourthDimRenderState;
import bee.insanity.item.DemoniteShard;
import bee.insanity.registry.ModComponents;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import java.util.List;
import java.util.Random;

@Mixin(AbstractContainerScreen.class)
public class ContainerScreenMixin {

    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;getItem()Lnet/minecraft/world/item/ItemStack;"), method = "extractSlot")
    private ItemStack wawa(ItemStack original) {
        if (original.getItem() instanceof DemoniteShard && Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            List<Item> list = player.registryAccess().getOrThrow(Registries.ITEM).value().stream().toList();
            ItemStack newStack = list.get(new Random().nextInt(0, list.size())).getDefaultInstance();

            if (player.tickCount % 15 == 0) {
                original.set(ModComponents.STACK, newStack);
            }
            return original.getOrDefault(ModComponents.STACK, newStack);
        }

        return original;
    }

}
