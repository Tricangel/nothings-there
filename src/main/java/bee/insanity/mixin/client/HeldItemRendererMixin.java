package bee.insanity.mixin.client;

import bee.insanity.NothingsThere;
import bee.insanity.registry.InsanityEntityComponents;
import bee.insanity.registry.InsanityItems;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;
import java.util.Random;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @ModifyVariable(at = @At("HEAD"), method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;I)V", argsOnly = true)
    private ItemStack init(ItemStack stack, LivingEntity entity) {
        if (stack.isOf(InsanityItems.DEMONITE_SHARD) && !NothingsThere.getDemoniteNearby(entity.getBlockPos(), entity.getEntityWorld())) {
            List<Item> list = entity.getRegistryManager().getOrThrow(RegistryKeys.ITEM).stream().toList();
            if (entity.age % 15 == 0) {
                InsanityEntityComponents.RENDERASITEM.get(entity).setStack(new ItemStack(list.get(new Random().nextInt(0, list.size()))));
            }
            stack = InsanityEntityComponents.RENDERASITEM.get(entity).getStack();
        }
        return stack;
    }




}