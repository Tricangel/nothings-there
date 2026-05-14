package bee.insanity.item;

import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.equine.Horse;
import net.minecraft.world.entity.animal.equine.Variant;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.Nullable;

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
        }

    }
}
