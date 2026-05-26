package bee.insanity.block;

import bee.insanity.registry.ModItems;
import bee.insanity.registry.ModSounds;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AngryAirBlock extends Block {
    public AngryAirBlock(Properties properties) {
        super(properties);
    }


    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        Vec3 entityPos = entity.position();
        Vec3 blockPos = pos.getCenter();
        Vec3 movement = new Vec3(entityPos.x - blockPos.x, entityPos.y - blockPos.y, entityPos.z - blockPos.z);
        entity.setDeltaMovement(movement.scale(0.5));
        entity.playSound(ModSounds.BOMB_EXPLODE);
        level.removeBlock(pos, false);
        super.entityInside(state, level, pos, entity, effectApplier, isPrecise);
    };


    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.is(Items.GLASS_BOTTLE)) {
            player.addItem(this.getCloneItemStack(level, pos, state, false));
            itemStack.shrink(1);
            player.playSound(SoundEvents.BOTTLE_FILL);
            level.removeBlock(pos, false);
        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected VoxelShape getEntityInsideCollisionShape(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
        return Shapes.block();
    }


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return context.isHoldingItem(Items.GLASS_BOTTLE) ? Shapes.block() : Shapes.empty();
    }
}
