package duke;

import duke.tasks.DeadLineTask;
import duke.tasks.EventTask;
import duke.tasks.TodoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void todoEqualsTest() {
        TodoTask task1 = new TodoTask("test");
        TodoTask task2 = new TodoTask("test");
        assertEquals(task1.equals(task2), true);
    }

    @Test
    public void todoNotEqualsTest() {
        TodoTask task1 = new TodoTask("hello");
        TodoTask task2 = new TodoTask("bye");
        assertEquals(task1.equals(task2), false);
    }

    @Test
    public void eventEqualsTest() {
        EventTask task1 = new EventTask("test", MyDateTime.parse(" 12/12/1234 1234"));
        EventTask task2 = new EventTask("test", MyDateTime.parse(" 12/12/1234 1234"));
        assertEquals(task1.equals(task2), true);
    }

    @Test
    public void eventNotEqualsTest() {
        EventTask task1 = new EventTask("hello", MyDateTime.parse(" 12/12/1234 1234"));
        EventTask task2 = new EventTask("bye", MyDateTime.parse(" 12/12/1234 1234"));
        assertEquals(task1.equals(task2), false); // only des. changed

        EventTask task3 = new EventTask("test", MyDateTime.parse(" 12/12/1234 2359"));
        EventTask task4 = new EventTask("test", MyDateTime.parse(" 12/12/1234 1234"));
        assertEquals(task3.equals(task4), false); // only time changed

        assertEquals(task1.equals(task3), false); // des. and time changed
    }

    @Test
    public void deadlineEqualsTest() {
        DeadLineTask task1 = new DeadLineTask("test", MyDateTime.parse(" 12/12/1234 1234"));
        DeadLineTask task2 = new DeadLineTask("test", MyDateTime.parse(" 12/12/1234 1234"));
        assertEquals(task1.equals(task2), true);
    }

    @Test
    public void deadlineNotEqualsTest() {
        DeadLineTask task1 = new DeadLineTask("hello", MyDateTime.parse(" 12/12/1234 1234"));
        DeadLineTask task2 = new DeadLineTask("bye", MyDateTime.parse(" 12/12/1234 1234"));
        assertEquals(task1.equals(task2), false); // only des. changed

        DeadLineTask task3 = new DeadLineTask("test", MyDateTime.parse(" 12/12/1234 2359"));
        DeadLineTask task4 = new DeadLineTask("test", MyDateTime.parse(" 12/12/1234 1234"));
        assertEquals(task3.equals(task4), false); // only time changed

        assertEquals(task1.equals(task3), false); // des. and time changed
    }
}
