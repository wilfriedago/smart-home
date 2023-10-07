package command.core;

import device.core.Device;

public class IllegalCommandException extends IllegalArgumentException {
    public IllegalCommandException(Command command, Device device) {
        super("Not possible to perform " + command.getClass().getName() + " on device of type " + device.getType());
    }
}
