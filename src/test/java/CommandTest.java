import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Command;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * A test class wrapping around the test methods for the class Command.
 */
public class CommandTest {
    @Test
    public void addToList_simpleTodo_success() {
        TaskList taskListExpected = new TaskList();
        taskListExpected.add(new Todo("read book", 2));

        TaskList taskListActual = new TaskList();
        Command command = new Command(TaskType.TODO, "read book", 2);
        command.execute(taskListActual);

        assertEquals(taskListExpected, taskListActual);
    }
}
