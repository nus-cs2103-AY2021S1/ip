package duke.commands;

import static duke.utils.Messages.MESSAGE_DELETE_TASK;
import static duke.utils.Messages.MESSAGE_NO_SUCH_TASK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.NoSuchTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Todo;

public class DeleteCommandTest {

    @Test
    public void execute_validIndex_success() {
        DeleteCommand command = new DeleteCommand(0);
        TaskList taskList = new TaskList();
        Todo todo = new Todo("description");
        taskList.addTask(todo);
        CommandResult actual = command.execute(taskList);
        String response = String.format("%s\t\t%s\n\t %s", MESSAGE_DELETE_TASK,
                todo.toString(), taskList.tasksRemaining());
        CommandResult expected = new CommandResult(response, false);
        assertEquals(expected, actual);
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        try {
            DeleteCommand command = new DeleteCommand(0);
            CommandResult actual = command.execute(new TaskList());
            fail();
        } catch (NoSuchTaskException e) {
            assertEquals(MESSAGE_NO_SUCH_TASK, e.getMessage());
        }
    }
}
