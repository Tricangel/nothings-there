package bee.insanity.cca;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v8.component.CardinalComponent;

public class BooleanComponent implements CardinalComponent {
    private String name;
    private boolean bool = false;
    public BooleanComponent(String name) {
        this.name = name;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public boolean getBool() {
        return bool;
    }

    @Override
    public void readData(ValueInput valueInput) {
        bool = valueInput.getBooleanOr(name, false);
    }

    @Override
    public void writeData(ValueOutput valueOutput) {
        valueOutput.putBoolean(name, bool);
    }
}
