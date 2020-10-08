package duke.commands;

import static duke.utils.Messages.MESSAGE_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasklist.TaskList;
import duke.tasks.Todo;

public class ListCommandTest {

    @Test
    public void execute_emptyList_success() {
        ListCommand command = new ListCommand();
        CommandResult actual = command.execute(new TaskList());
        CommandResult expected = new CommandResult(MESSAGE_LIST, false);
        assertEquals(expected, actual);
    }

    @Test
    public void execute_nonEmptyList_success() {
        ListCommand command = new ListCommand();
        TaskList taskList = new TaskList();
        Todo todo = new Todo("description");
        taskList.addTask(todo);
        CommandResult actual = command.execute(taskList);
        String response = MESSAGE_LIST + "\t 1.[T][\u2718] description";
        CommandResult expected = new CommandResult(response, false);
        assertEquals(expected, actual);
    }
}
