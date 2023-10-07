import hub.Schedule;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class ScheduleTest {

    @Test
    public void testValidSchedule() {
        Schedule schedule = new Schedule("from 11:30 to 23:00");
        LocalTime from = schedule.getFrom();
        LocalTime to = schedule.getTo();

        Assert.assertEquals(11, from.getHour());
        Assert.assertEquals(30, from.getMinute());

        Assert.assertEquals(23, to.getHour());
        Assert.assertEquals(0, to.getMinute());
    }

    @Test
    public void testInvalidSchedule() {
        try {
            Schedule schedule = new Schedule("11:30 let's see");
            Assert.fail("Should throw exception");
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid schedule"));
        }
    }
}
