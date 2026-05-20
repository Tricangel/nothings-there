package bee.insanity.mixin.client;

import bee.insanity.item.DemoniteShard;
import bee.insanity.registry.ModComponents;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.state.ItemEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(ItemEntityRenderer.class)
public class ItemRendererMixin {

    @Shadow
    @Final
    private ItemModelResolver itemModelResolver;

    @Inject(at = @At(value = "TAIL"), method = "extractRenderState(Lnet/minecraft/world/entity/item/ItemEntity;Lnet/minecraft/client/renderer/entity/state/ItemEntityRenderState;F)V")
    private void wawa(ItemEntity entity, ItemEntityRenderState state, float partialTicks, CallbackInfo ci) {
        if (entity.getItem().getItem() instanceof DemoniteShard) {
            List<Item> stacks = new ArrayList<>(entity.registryAccess().lookupOrThrow(Registries.ITEM).stream().toList());
            stacks.remove(Items.AIR);
            ItemStack newStack = stacks.get(new Random().nextInt(stacks.size())).getDefaultInstance();

            if (entity.tickCount % 15 == 0 || entity.getItem().get(ModComponents.STACK) == null) {
                ItemStack stack = entity.getItem().copy();
                stack.set(ModComponents.STACK, newStack);
                entity.setItem(stack);
            }
            state.extractItemGroupRenderState(entity, entity.getItem().getOrDefault(ModComponents.STACK, newStack), this.itemModelResolver);
        }
    }

}
