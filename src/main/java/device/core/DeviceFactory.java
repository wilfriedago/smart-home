package device.core;

import device.Conditioner;
import device.Lamp;
import device.TV;

public class DeviceFactory {
    public static Device of(DeviceType type) {
        return switch (type) {
            case LAMP -> new Lamp();
            case MEDIA -> new TV();
            case CONDITIONER -> new Conditioner();
        };
    };
}
