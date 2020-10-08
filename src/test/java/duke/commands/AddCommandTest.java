package duke.commands;

import static duke.utils.Messages.MESSAGE_ADD_TASK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasklist.TaskList;
import duke.tasks.Todo;

public class AddCommandTest {

    @Test
    public void testExecute() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        AddCommand addCommand = new AddCommand(todo);
        CommandResult actual = addCommand.execute(taskList);
        String expectedResponse = String.format("%s\t\t%s\n\t %s", MESSAGE_ADD_TASK, todo.toString(),
                taskList.tasksRemaining());
        CommandResult expected = new CommandResult(expectedResponse, false);
        assertEquals(expected, actual);
    }
}
