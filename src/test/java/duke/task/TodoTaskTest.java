package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    @Test
    public void getData_todoTaskNotDone_correctOutput() {
        TodoTask task = new TodoTask("test");
        String[] str = task.getData().split("\\|");
        assertEquals("T", str[0]);
        assertEquals("0", str[1]);
        assertEquals("test", str[2]);
    }

    @Test
    public void getData_todoTaskDone_correctOutput() {
        TodoTask task = new TodoTask("test").markAsDone();
        String[] str = task.getData().split("\\|");
        assertEquals("T", str[0]);
        assertEquals("1", str[1]);
        assertEquals("test", str[2]);
    }

    @Test
    public void toString_todoTaskNotDone_correctOutput() {
        TodoTask task = new TodoTask("test");
        String str = task.toString();
        assertEquals("[T][\u2718] test", str);
    }

    @Test
    public void toString_todoTaskDone_correctOutput() {
        TodoTask task = new TodoTask("test").markAsDone();
        String str = task.toString();
        assertEquals("[T][\u2713] test", str);
    }

    @Test
    public void contains_correctKeyword_true() {
        assertTrue(new TodoTask("test").contains("test"));
    }

    @Test
    public void contains_wrongKeyword_false() {
        assertFalse(new TodoTask("test").contains("wrong"));
    }
}
