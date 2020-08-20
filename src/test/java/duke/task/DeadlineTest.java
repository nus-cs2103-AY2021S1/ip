package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        LocalDate schedule = LocalDate.parse("2020-08-20");
        Deadline deadline = new Deadline("deadline1", false, schedule);
        assertEquals("[D][✘] deadline1 (by: Aug 20 2020)", deadline.toString());
    }

    @Test
    public void getScheduleTest() {
        LocalDate schedule = LocalDate.parse("2020-08-20");
        Deadline deadline = new Deadline("deadline1", false, schedule);
        assertEquals("Aug 20 2020", deadline.getSchedule());
    }

    @Test
    public void completeTest() {
        LocalDate schedule = LocalDate.parse("2020-08-20");
        Deadline nonCompletedDeadline = new Deadline("deadline1", false, schedule);
        Deadline completedDeadline = new Deadline("deadline1", true, schedule);
        assertEquals(completedDeadline.toString(), nonCompletedDeadline.complete().toString());
    }

    @Test
    public void formatTest() {
        LocalDate schedule = LocalDate.parse("2020-08-20");
        Deadline deadline = new Deadline("deadline1", false, schedule);
        assertEquals("D | 0 | deadline1 | Aug 20 2020", deadline.format());
    }
}
