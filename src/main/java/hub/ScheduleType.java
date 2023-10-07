package hub;

public enum ScheduleType {
    REPEATING, SIMPLE;

    public static ScheduleType getScheduleType(String schedule) {
        if (schedule.toLowerCase().contains("from") && schedule.toLowerCase().contains("to")) {
            return REPEATING;
        } else if (schedule.toLowerCase().contains("always")) {
            return SIMPLE;
        } else {
            return null;
        }
    }
}
