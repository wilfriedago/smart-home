package device.core;

import command.core.Command;

public abstract class Device {

    protected String deviceName;

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public abstract DeviceType getType();

    public abstract void accept(Command command);

    @Override
    public String toString() {
        return "Device{" +
                "deviceName='" + deviceName + '\'' + ", type='" + getType() + '\'' +
                '}';
    }
}
