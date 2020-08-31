package seedu.duke;

import seedu.duke.todo.Todo;
import seedu.duke.todo.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void markAsDoneTest(){
        Task task = new Todo("borrow book");
        assertEquals("\u2718", task.getStatusIcon()); // cross
        task.markAsDone();
        assertEquals("\u2713", task.getStatusIcon()); // tick
    }
}
