import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import juke.Storage;
import juke.TaskList;
import juke.command.TaskCommand;
import juke.task.Todo;

public class TaskCommandTest {

    @Test
    public void testCommandExecution() {
        Storage storage = new Storage();
        TaskList taskList = new TaskList(new ArrayList<>());
        Todo todo = new Todo("Eat apples");
        TaskCommand taskCommand = new TaskCommand(todo);

        String expectedText = "Alright matey, I've added this task:\n[T][\u2718] Eat apples\n"
                + "You have 1 tasks in total.";
        String actualText = taskCommand.executeCommand(taskList, storage);

        assertEquals(expectedText, actualText);
    }
}
