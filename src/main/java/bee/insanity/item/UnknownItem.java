package bee.insanity.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class UnknownItem extends Item {
    public UnknownItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onCraftedBy(ItemStack itemStack, Player player) {
        super.onCraftedBy(itemStack, player);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {



        super.inventoryTick(itemStack, level, owner, slot);
    }
}
