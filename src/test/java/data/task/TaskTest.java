package data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void dateFinderTest() {
        assertEquals(Task.containsDate("dhosadhaus /at 1999-09-09"), 15);
    }

    /**
     * test for date parsing
     */
    @Test
    public void dateConvertTest() {
        Task testTask = new Task("todo", "duhsaodah /at 1999-09-09");
        assertEquals(testTask.getDate(), "Sep 09 1999");
    }
}
