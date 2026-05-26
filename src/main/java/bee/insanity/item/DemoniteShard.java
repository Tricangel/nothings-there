package bee.insanity.item;

import bee.insanity.NothingsThere;
import bee.insanity.data.DemonPlayers;
import bee.insanity.registry.ModBlocks;
import bee.insanity.registry.ModComponents;
import bee.insanity.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.equine.Variant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DemoniteShard extends Item {
    public DemoniteShard(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        super.inventoryTick(itemStack, level, owner, slot);
        boolean demoniteNearby = false;

        for (Direction direction : Direction.allShuffled(RandomSource.create())) {
            if (level.getBlockState(owner.blockPosition().relative(direction)).is(Blocks.RAW_COPPER_BLOCK)) {
                demoniteNearby = true;
            }
        }
        if (demoniteNearby) {
            itemStack.set(DataComponents.HORSE_VARIANT, Variant.CHESTNUT);
        } else itemStack.set(DataComponents.HORSE_VARIANT, Variant.CREAMY);

        List<Item> stacks = new ArrayList<>(owner.registryAccess().lookupOrThrow(Registries.ITEM).stream().toList());
        stacks.remove(Items.AIR);
        ItemStack newStack = stacks.get(new Random().nextInt(stacks.size())).getDefaultInstance();

        if (owner.tickCount % 15 == 0 || itemStack.get(ModComponents.STACK) == null) {
            itemStack.set(ModComponents.STACK, newStack);
        }

    }

    @Override
    public Component getName(ItemStack itemStack) {
        return super.getName(itemStack.getOrDefault(ModComponents.STACK, Items.ACACIA_BOAT.getDefaultInstance()));
    }


    @Override
    public boolean allowComponentsUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).is(ModItems.DEMONITE_SHARD)) {
            HitResult result = player.pick(player.blockInteractionRange(), 1, true);

            if (result.getType().equals(HitResult.Type.MISS)) {
                player.playSound(SoundEvents.RESPAWN_ANCHOR_DEPLETE.value());
                level.setBlockAndUpdate(BlockPos.containing(result.getLocation()), ModBlocks.ANGRY_AIR.defaultBlockState());
                player.getItemInHand(hand).shrink(1);
                return InteractionResult.SUCCESS;
            }

        }
        return InteractionResult.FAIL;
    }
}
