package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testSaveText() {
        assertEquals("D | 1 | test1 | Sep 09 2020 10:00AM", new Deadline("test1", "Sep 09 2020 10:00AM").saveText(1));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][✘] test1 (by: Sep 09 2020 10:00AM)", new Deadline("test1", "Sep 09 2020 10:00AM").toString());
    }
}
