package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline dl = new Deadline("test", "2019-02-11 14:00");
    @Test
    public void testStringConversion() {
        assertEquals("[D][✘] test (by: Feb 11 2019 2:00 PM)", dl.toString());
    }

}
