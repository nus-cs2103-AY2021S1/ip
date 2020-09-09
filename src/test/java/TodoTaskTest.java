import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TodoTask;

public class TodoTaskTest {
    private static final String tick = "\u2713";
    private static final String cross = "\u2718";
    private static final String description = "TESTING";
    @Test
    public void todoStringTest() {
        TodoTask task = new TodoTask("TESTING");
        assertEquals(task.toString(), "[T]" + "[" + cross + "] " + description);
        task.markAsDone();
        assertEquals(task.toString(), "[T]" + "[" + tick + "] " + description);
    }
}
