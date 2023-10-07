package device;

import command.core.Command;
import device.core.Device;
import device.core.DeviceType;
import device.core.Switchable;

public class TV extends Device implements Switchable {

    @Override
    public DeviceType getType() {
        return DeviceType.MEDIA;
    }

    public void tuneToChannel(String channel) {
        System.out.println("You are watching " + channel);
    }

    @Override
    public void switchOn() {
        System.out.println("TV is on");
    }

    @Override
    public void switchOff() {
        System.out.println("TV is off");
    }

    @Override
    public void accept(Command command) {
        command.visit(this);
    }
}
