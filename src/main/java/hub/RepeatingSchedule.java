package hub;

import command.core.Command;
import command.core.Undoable;
import device.core.Device;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A repeating schedule is a repeating simple schedule, so we can use the Decorator Pattern to use the simple scheduler.
 */
public class RepeatingSchedule implements DeviceSchedule {

    private final Device device;
    private final Undoable command;
    private final LocalTime from;
    private final LocalTime to;
    private final ScheduledExecutorService executorService;

    public RepeatingSchedule(Device device, Undoable command, LocalTime from, LocalTime to, ScheduledExecutorService scheduledExecutorService) {
        this.device = device;
        this.command = command;
        this.from = from;
        this.to = to;
        this.executorService = scheduledExecutorService;
    }

    @Override
    public void execute() {
        LocalTime now = LocalTime.now();

        // Repeating period in minutes: Every day
        long day = 24 * 60;

        // Delay between now and from in minutes
        // now ---> from ---> to
        long startDelay = now.until(from, ChronoUnit.MINUTES);
        long endDelay = now.until(to, ChronoUnit.MINUTES);

        // When the time of running is after "from" or "to" the result will be negative. We should aim for the next day.
        // from ---> now ---> to
        // OR
        // from ---> to ---> now
        if (startDelay < 0) {
            startDelay += day;
        }

        if (endDelay < 0) {
            endDelay += day;
        }

        // Schedule a simple schedule in the future (from)
        DeviceSchedule start = new SimpleSchedule(device, (Command) command);
        executorService.scheduleAtFixedRate(start::execute, startDelay, day, TimeUnit.MINUTES);
        System.out.println("Scheduled to execute " + start + " in " + startDelay + " minutes");

        // Schedule an undo command in the future (to)
        DeviceSchedule end = new SimpleSchedule(device, command.undo());
        executorService.scheduleAtFixedRate(end::execute, endDelay, day, TimeUnit.MINUTES);
        System.out.println("Scheduled to execute " + end + " in " + endDelay + " minutes");
    }

    @Override
    public String toString() {
        return "RepeatingSchedule{" +
                "device=" + device +
                ", command=" + command +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
