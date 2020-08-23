package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testSaveText() {
        assertEquals("E | 1 | test1 | Sep 09 2020 10:00AM", new Event("test1","Sep 09 2020 10:00AM").saveText(1));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][✘] test1 (at: Sep 09 2020 10:00AM)", new Event("test1","Sep 09 2020 10:00AM").toString());
    }
}
