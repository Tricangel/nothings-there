package bee.insanity.mixin;

import bee.insanity.NothingsThere;
import bee.insanity.registry.InsanityEntityComponents;
import bee.insanity.registry.InsanityItems;
import net.minecraft.client.render.entity.state.ItemStackEntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;
import java.util.Random;

@Mixin(ItemStackEntityRenderState.class)
public class ItemStackEntityRenderStateMixin {

	@ModifyVariable(at = @At("HEAD"), method = "update", argsOnly = true)
	private ItemStack init(ItemStack stack, Entity entity) {
		if (stack.isOf(InsanityItems.DEMONITE_SHARD) || stack.isOf(InsanityItems.DEMONITE)) {

			if (entity != null && !NothingsThere.getDemoniteNearby(entity.getBlockPos(), entity.getEntityWorld())) {
				List<Item> list = entity.getRegistryManager().getOrThrow(RegistryKeys.ITEM).stream().toList();

				if (entity.age % 15 == 0) {
					InsanityEntityComponents.RENDERASITEM.get(entity).setStack(new ItemStack(list.get(new Random().nextInt(0, list.size()))));

				}

                stack = InsanityEntityComponents.RENDERASITEM.get(entity).getStack();

            }
		}
			return stack;

	}


}