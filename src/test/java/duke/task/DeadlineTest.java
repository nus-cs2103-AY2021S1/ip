package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private Deadline dl = new Deadline("test", "2019-02-11 14:00");
    @Test
    public void testStringConversion() {
        assertEquals("[D][\u2718] test (by: Feb 11 2019 2:00 PM)", dl.toString());
    }

}
