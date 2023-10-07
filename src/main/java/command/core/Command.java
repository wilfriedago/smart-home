package command.core;

import command.core.IllegalCommandException;
import device.Conditioner;
import device.Lamp;
import device.TV;
import device.core.Device;

/**
 * Each Command object is implemented as a "visitor" that performs an action on the provided device instance.
 * For convenience, the Command name is used instead of Visitor
 *
 * @see <a href="https://refactoring.guru/pattern/visitor">Visitor Pattern</a>
 */
public abstract class Command {

    protected String value;

    public Command(String value) {
        this.value = value.toLowerCase();
    }

    /**
     * Each device class has a general visit() method here with empty body
     * Every command can implement the proper methods to which it applies.
     * Throwing an IllegalCommandException by default makes sure that validation is automatically done and the visit()
     * method is valid only if it is explicitly overridden in the subclass.
     */
    public void visit(Lamp lamp) {
        throw new IllegalCommandException(this, lamp);
    }

    public void visit(TV tv) {
        throw new IllegalCommandException(this, tv);
    }

    public void visit(Conditioner conditioner) {
        throw new IllegalCommandException(this, conditioner);
    }

    @Override
    public String toString() {
        return "Command{" +
                "value='" + value + '\'' +
                '}';
    }
}
