package bee.insanity.cca;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.Component;

public class RenderAsItemComponent implements Component {

    private ItemStack stack = Items.ACACIA_FENCE.getDefaultStack();

    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public void readData(ReadView readView) {
    }

    @Override
    public void writeData(WriteView writeView) {

    }
}
