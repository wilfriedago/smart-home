package hub;

import command.core.Command;
import device.core.Device;

/**
 * This schedule is a simple strategy for invoking a command on a device.
 * It executes only once and goes away.
 */
public class SimpleSchedule implements DeviceSchedule {

    private final Device device;
    private final Command command;

    public SimpleSchedule(Device device, Command command) {
        this.device = device;
        this.command = command;
    }

    @Override
    public void execute() {
        System.out.println("Executed " + this);
        device.accept(command);
    }

    @Override
    public String toString() {
        return "SimpleSchedule{" +
                "device=" + device +
                ", command=" + command +
                '}';
    }
}
