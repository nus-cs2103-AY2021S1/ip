package duke.commands;

import static duke.utils.Messages.MESSAGE_DONE_TASK;
import static duke.utils.Messages.MESSAGE_NO_SUCH_TASK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.NoSuchTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Todo;

public class DoneCommandTest {

    @Test
    public void execute_validInput_success() {
        Todo todo = new Todo("description");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        DoneCommand command = new DoneCommand(0);
        CommandResult actual = command.execute(taskList);
        String response = String.format("%s\t\t%s", MESSAGE_DONE_TASK, todo.toString());
        CommandResult expected = new CommandResult(response, false);
        assertEquals(actual, expected);
    }

    @Test
    public void execute_invalidInput_exceptionThrown() {
        try {
            DoneCommand command = new DoneCommand(0);
            command.execute(new TaskList());
            fail();
        } catch (NoSuchTaskException e) {
            assertEquals(MESSAGE_NO_SUCH_TASK, e.getMessage());
        }

    }
}
