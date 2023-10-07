package device;

import command.core.Command;
import device.core.Device;
import device.core.DeviceType;
import device.core.Switchable;

public class Conditioner extends Device implements Switchable {

    private boolean isOn;

    @Override
    public DeviceType getType() {
        return DeviceType.CONDITIONER;
    }

    public void setTemperature(int temperature) {
        if (temperature < 16 || temperature > 30) {
            throw new IllegalArgumentException("The requested temperature is not within the supported range");
        }

        // A switched off conditioner usually does not work
        if (!isOn) {
            switchOn();
        }

        System.out.println("Conditioner set to temperature " + temperature + " degrees");
    }

    @Override
    public void accept(Command command) {
        command.visit(this);
    }

    @Override
    public void switchOn() {
        isOn = true;
    }

    @Override
    public void switchOff() {
        isOn = false;
    }
}
