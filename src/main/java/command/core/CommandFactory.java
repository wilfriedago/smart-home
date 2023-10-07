package command.core;

import command.AdjustTemperature;
import command.Switch;

public class CommandFactory {
    public static Command of(String value) {
        if ("on".equalsIgnoreCase(value)) {
            return new Switch("on");
        } else if ("off".equalsIgnoreCase(value)) {
            return new Switch("off");
        } else {
            // If it is a number
            try {
                // Better to use a regexp, however trying to convert to a number will also work (lazy solution)
                Integer.parseInt(value);
                return new AdjustTemperature(value);
            } catch (NumberFormatException e) {
                // Not a number
            }
        }
        throw new IllegalArgumentException("Not a valid value for any command.");
    };
}
