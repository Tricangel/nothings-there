package bee.insanity.mixin.client;

import bee.insanity.registry.ModComponents;
import bee.insanity.registry.ModItems;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmedEntityRenderState.class)
public abstract class PlayerItemInHandMixin {


    @Inject(at = @At(value = "TAIL"), method = "extractArmedEntityRenderState")
    private static void renderElseDemonite(LivingEntity entity, ArmedEntityRenderState state, ItemModelResolver itemModelResolver, float partialTicks, CallbackInfo ci) {
        if (entity.getItemHeldByArm(HumanoidArm.RIGHT).is(ModItems.DEMONITE_SHARD)) {
            itemModelResolver.updateForLiving(
                    state.rightHandItemState, entity.getItemHeldByArm(HumanoidArm.RIGHT).getOrDefault(ModComponents.STACK, Items.ACACIA_BOAT.getDefaultInstance()), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, entity
            );

        }

        if (entity.getItemHeldByArm(HumanoidArm.LEFT).is(ModItems.DEMONITE_SHARD)) {
            itemModelResolver.updateForLiving(
                    state.leftHandItemState, entity.getItemHeldByArm(HumanoidArm.LEFT).getOrDefault(ModComponents.STACK, Items.ACACIA_BOAT.getDefaultInstance()), ItemDisplayContext.THIRD_PERSON_LEFT_HAND, entity);
        }


    }

}
