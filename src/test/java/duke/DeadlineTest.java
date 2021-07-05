package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    public void testDeadlines() {
        assertEquals("D | 0 | meeting | 2020-08-16", new Deadline("meeting", "2020-08-16").writeToFile());
    }
}
