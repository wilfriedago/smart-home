import command.Switch;
import device.Conditioner;
import device.Lamp;
import device.TV;
import hub.RepeatingSchedule;
import hub.DeviceSchedule;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SimpleAppTest {
    public static void main(String[] args) {

        Lamp lamp = new Lamp();
        lamp.setDeviceName("living_room");

        TV tv = new TV();
        tv.setDeviceName("tv");

        Conditioner conditioner = new Conditioner();
        conditioner.setDeviceName("main");

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Switch command = new Switch("on");
        LocalTime from = LocalTime.of(16, 56);
        LocalTime to = LocalTime.of(16, 57);

        DeviceSchedule schedule = new RepeatingSchedule(lamp, command, from, to, executorService);
        schedule.execute();
    }
}
