package bee.insanity.cca;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import org.ladysnake.cca.api.v8.component.CardinalComponent;

public class FourthDimension implements CardinalComponent, CommonTickingComponent {
    private int timeIn4thDim = 0;

    public int getTimeIn4thDim() {
        return timeIn4thDim;
    }

    public void setTimeIn4thDim(int timeIn4thDim) {
        this.timeIn4thDim = timeIn4thDim;
    }

    @Override
    public void readData(ValueInput readView) {
        timeIn4thDim = readView.getIntOr("timeIn4thDim", 0);
    }

    @Override
    public void writeData(ValueOutput writeView) {
        writeView.putInt("timeIn4thDim", timeIn4thDim);
    }

    @Override
    public void tick() {
        if (timeIn4thDim > 0) {
            timeIn4thDim--;
            if (timeIn4thDim == 0) timeIn4thDim = -100;
        } else if (timeIn4thDim < 0) timeIn4thDim++;
    }
}
