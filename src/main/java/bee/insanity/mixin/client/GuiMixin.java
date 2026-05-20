package bee.insanity.mixin.client;

import bee.insanity.item.DemoniteShard;
import bee.insanity.registry.ModComponents;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(Gui.class)
public class GuiMixin {

    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getOffhandItem()Lnet/minecraft/world/item/ItemStack;"), method = "extractItemHotbar")
    private ItemStack wawa(ItemStack original) {
        if (original.getItem() instanceof DemoniteShard && Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            List<Item> stacks = new ArrayList<>(player.registryAccess().lookupOrThrow(Registries.ITEM).stream().toList());
            stacks.remove(Items.AIR);
            ItemStack newStack = stacks.get(new Random().nextInt(stacks.size())).getDefaultInstance();
            if (player.tickCount % 15 == 0 || original.get(ModComponents.STACK) == null) {
                original.set(ModComponents.STACK, newStack);
            }
            ItemStack returnStack = original.getOrDefault(ModComponents.STACK, newStack);
            returnStack.setCount(original.getCount());
            return returnStack;
        }

        return original;
    }

    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;getItem(I)Lnet/minecraft/world/item/ItemStack;"), method = "extractItemHotbar")
    private ItemStack wawa2(ItemStack original) {
        if (original.getItem() instanceof DemoniteShard && Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            List<Item> stacks = new ArrayList<>(player.registryAccess().lookupOrThrow(Registries.ITEM).stream().toList());
            stacks.remove(Items.AIR);
            ItemStack newStack = stacks.get(new Random().nextInt(stacks.size())).getDefaultInstance();
            if (player.tickCount % 15 == 0 || original.get(ModComponents.STACK) == null) {
                original.set(ModComponents.STACK, newStack);
            }
            ItemStack returnStack = original.getOrDefault(ModComponents.STACK, newStack);
            returnStack.setCount(original.getCount());
            returnStack.setDamageValue(original.getDamageValue());
            return returnStack;
        }

        return original;
    }

}
