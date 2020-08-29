package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void of_success() {
        String task = "party";
        String date = "2020-02-02";
        String time = "20:20";
        boolean done = false;
        Event testUnit = Event.of(task, date, time, done);
        assertEquals(testUnit.toString(), "[E][✘] party (at: Feb 2, 2020 8:20 PM)");
    }
}
