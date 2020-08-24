package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        String expected = "[D][✘] eat (by: 22 February 2020, 12:30 PM)";
        assertEquals(expected, new Deadline("eat", "22/02/2020 12:30").toString());
    }

    @Test
    public void testToFileString() {
        String expected = "D | 0 | eat | 22/02/2020 00:00";
        assertEquals(expected, new Deadline("eat", "22/02/2020 00:00").toFileString());
    }
}
