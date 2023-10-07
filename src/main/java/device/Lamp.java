package device;

import command.core.Command;
import device.core.Device;
import device.core.DeviceType;
import device.core.Switchable;

public class Lamp extends Device implements Switchable {

    private boolean isOn;

    @Override
    public DeviceType getType() {
        return DeviceType.LAMP;
    }

    @Override
    public void switchOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("Lamp " + deviceName + " is on");
        }
    }

    @Override
    public void switchOff() {
        if (isOn) {
            isOn = false;
            System.out.println("Lamp " + deviceName + " is off");
        }
    }

    @Override
    public void accept(Command command) {
        command.visit(this);
    }
}
