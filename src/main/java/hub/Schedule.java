package hub;

import java.time.LocalTime;

public class Schedule {
    private final LocalTime from;
    private final LocalTime to;

    /**
     * "from xx:xx to xx"xx"
     */
    public Schedule(String schedule) {

        if (ScheduleType.getScheduleType(schedule) != ScheduleType.REPEATING) {
            throw new IllegalArgumentException("Invalid schedule: " + schedule);
        }

        String[] split = schedule.split(" ");
        String fromStr = split[1], toStr = split[3];
        String[] fromParts = fromStr.split(":"), toParts = toStr.split(":");

        from = LocalTime.of(Integer.parseInt(fromParts[0]), Integer.parseInt(fromParts[1]));
        to = LocalTime.of(Integer.parseInt(toParts[0]), Integer.parseInt(toParts[1]));
    }

    public LocalTime getFrom() {
        return from;
    }

    public LocalTime getTo() {
        return to;
    }
}
