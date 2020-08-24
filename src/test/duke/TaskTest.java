package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    public void testSetDone_notDoneTask_success() {
        assertEquals(new Task("test", true), new Task("test", false).setDone());
    }

    @Test
    public void testFormatTask_notDoneTask_success() {
        assertEquals("P | X | test", new Task("test", false).formatTask());
    }
}