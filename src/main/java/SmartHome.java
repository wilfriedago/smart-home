import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import command.core.Command;
import command.core.CommandFactory;
import command.core.Undoable;
import device.core.Device;
import device.core.DeviceFactory;
import device.core.DeviceType;
import hub.DeviceSchedule;
import hub.RepeatingSchedule;
import hub.Schedule;
import hub.ScheduleType;
import hub.SimpleSchedule;

public class SmartHome {
    private final ScheduledExecutorService executorService;

    public SmartHome(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    public void shutdown() {
        System.out.println("Shut down SmartHome application");
        this.executorService.shutdown();
    }

    public static void main(String[] args) throws IOException {
        SmartHome smartHome = new SmartHome(Executors.newSingleThreadScheduledExecutor());

        // When we close the application, SmartHome.shutdown() will be called
        Runtime.getRuntime().addShutdownHook(new Thread(smartHome::shutdown));

        List<DeviceSchedule> devices = Files.readAllLines(Path.of("schedule.txt"))
                .stream()
                .map(smartHome::getSchedule)
                .filter(Objects::nonNull)
                .toList();

        // Print the loaded schedule
        // devices.forEach(System.out::println);

        devices.forEach(DeviceSchedule::execute);
    }

    protected DeviceSchedule getSchedule(String scheduleExpr) {
        // Line format: type name value schedule
        StringTokenizer tokenizer = new StringTokenizer(scheduleExpr, " ");

        // Extraction of the device type and creation using the DeviceFactory
        DeviceType type = DeviceType.valueOf(tokenizer.nextToken().toUpperCase());
        Device device = DeviceFactory.of(type);

        // Setting the device name
        device.setDeviceName(tokenizer.nextToken());

        // Extracting the value of the command and setting it up
        Command command = CommandFactory.of(tokenizer.nextToken());

        // Extracting the schedule and creating the 
        DeviceSchedule deviceSchedule = null;
        ScheduleType scheduleType = ScheduleType.getScheduleType(scheduleExpr);

        // Extracting the schedule and creating the DeviceSchedule
        if (scheduleType == ScheduleType.SIMPLE) {
            deviceSchedule = new SimpleSchedule(device, command);
        } else if (scheduleType == ScheduleType.REPEATING) {
            StringBuilder scheduleStr = new StringBuilder();
            while (tokenizer.hasMoreElements()) {
                scheduleStr.append(tokenizer.nextToken()).append(" ");
            }
            Schedule schedule = new Schedule(scheduleStr.toString());
            deviceSchedule = new RepeatingSchedule(device, (Undoable) command, schedule.getFrom(), schedule.getTo(),
                    executorService);
        } else {
            // ignore
        }


        return deviceSchedule;
    }
}
