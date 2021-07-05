package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventTest {
    @Test
    public void testEvents() {
        assertEquals("E | 0 | meeting | 2020-08-16", new Event("meeting", "2020-08-16").writeToFile());
    }
}
