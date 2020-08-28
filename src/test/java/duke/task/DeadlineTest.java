package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void of_success() {
        String task = "homework";
        String date = "2020-02-02";
        String time = "23:59";
        boolean done = true;
        Deadline testUnit = Deadline.of(task, date, time, done);
        assertEquals(testUnit.toString(), "[D][✓] homework (by: Feb 2, 2020 11:59 PM)");
    }
}
