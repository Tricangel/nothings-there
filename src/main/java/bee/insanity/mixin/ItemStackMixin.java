package bee.insanity.mixin;

import bee.insanity.registry.ModComponents;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(Inventory.class)
public abstract class ItemStackMixin {

    @ModifyExpressionValue(method = "hasRemainingSpaceForItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isSameItemSameComponents(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z"))
    private static boolean explodeWhenDemonite(boolean original, ItemStack a, ItemStack b) {
        ItemStack aStack = a.copy();
        ItemStack bStack = b.copy();
        aStack.remove(ModComponents.STACK);
        bStack.remove(ModComponents.STACK);
        if (!aStack.is(bStack.getItem())) {
            return false;
        } else {
            return (aStack.isEmpty() && bStack.isEmpty()) || Objects.equals(aStack.getComponents(), bStack.getComponents());
        }
    }



}
