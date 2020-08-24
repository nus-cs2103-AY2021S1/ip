package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getDescription() {
        assertEquals("do", new Task("do").getDescription());
    }

    @Test
    void completeTask() {
        Task t = new Task("do");
        t.completeTask();
        assertEquals(true, t.done);
    }

    @Test
    void testToString() {
        Task t = new Task("do");
        assertEquals("[✘] do", t.toString());

    }


    @Test
    void toFileString() {
        Task t = new Task("do");
        assertEquals("F\ndo\n", t.toFileString());
    }
}