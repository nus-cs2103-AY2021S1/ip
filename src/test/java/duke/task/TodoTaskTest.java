package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    public void getData_todoTaskNotDone_correctOutput() {
        TodoTask task = new TodoTask("test");
        String[] str = task.getData().split("\\|");
        assertEquals(str[0], "T");
        assertEquals(str[1], "0");
        assertEquals(str[2], "test");
    }

    @Test
    public void getData_todoTaskDone_correctOutput() {
        TodoTask task = new TodoTask("test").markAsDone();
        String[] str = task.getData().split("\\|");
        assertEquals(str[0], "T");
        assertEquals(str[1], "1");
        assertEquals(str[2], "test");
    }

    @Test
    public void toString_todoTaskNotDone_correctOutput() {
        TodoTask task = new TodoTask("test");
        String str = task.toString();
        assertEquals(str, "[T][\u2718] test");
    }

    @Test
    public void toString_todoTaskDone_correctOutput() {
        TodoTask task = new TodoTask("test").markAsDone();
        String str = task.toString();
        assertEquals(str, "[T][\u2713] test");
    }
}
