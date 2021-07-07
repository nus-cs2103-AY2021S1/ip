package duke.tasktest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TodoTask;

public class TodoTest {

    @Test
    public void addTodoTask_success() {
        TodoTask newTodoTask = new TodoTask("description");
        String expected = "[T][✘] description";
        assertEquals(expected, newTodoTask.toString());
    }

    @Test
    public void markTodoTaskAsDone_success() {
        TodoTask newTodoTask = new TodoTask("description");
        newTodoTask.markAsDone();
        String expected = "[T][✓] description";
        assertEquals(expected, newTodoTask.toString());
    }

}
