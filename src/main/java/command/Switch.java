package command;

import command.core.Command;
import command.core.Undoable;
import device.Conditioner;
import device.Lamp;
import device.TV;
import device.core.Switchable;

/**
 * A switch command performs on/off on any kind of device that supports it.
 * The device needs to implement the Switchable interface in order to make sure that it has the two switchOn() and switchOff() methods.
 * However, every device types handles its own switching on/off.
 *
 * @see Switchable
 */
public class Switch extends Command implements Undoable {

    public Switch(String value) {
        super(value);
    }

    @Override
    public void visit(Lamp lamp) {
        doSwitch(lamp);
    }

    @Override
    public void visit(TV tv) {
        doSwitch(tv);
    }

    @Override
    public void visit(Conditioner conditioner) {
        doSwitch(conditioner);
    }

    private void doSwitch(Switchable switchable) {
        switch (value) {
            case "on" -> switchable.switchOn();
            case "off" -> switchable.switchOff();
        }
    }

    @Override
    public Command undo() {
        String undoValue = "on".equals(value) ? "off" : "on";
        return new Switch(undoValue);
    }
}
