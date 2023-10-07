package command;

import command.core.Command;
import device.Conditioner;

public class AdjustTemperature extends Command {

    public AdjustTemperature(String value) {
        super(value);
    }

    @Override
    public void visit(Conditioner conditioner) {
        conditioner.setTemperature(Integer.parseInt(value));
    }
}
