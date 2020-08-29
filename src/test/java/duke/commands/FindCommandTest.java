package duke.commands;

import static duke.utils.Messages.MESSAGE_FIND;
import static duke.utils.Messages.MESSAGE_FIND_NO_MATCH;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasklist.TaskList;
import duke.tasks.Todo;

public class FindCommandTest {

    @Test
    public void execute_noMatch_showNoMatchFound() {
        FindCommand command = new FindCommand("word");
        CommandResult actual = command.execute(new TaskList());
        String response = MESSAGE_FIND_NO_MATCH;
        CommandResult expected = new CommandResult(response, false);
        assertEquals(expected, actual);
    }

    @Test
    public void execute_hasMatch_showMatchedTasks() {
        FindCommand command = new FindCommand("word");
        TaskList taskList = new TaskList();
        Todo matched = new Todo("has word");
        Todo unmatched = new Todo("no");
        taskList.addTask(matched);
        taskList.addTask(unmatched);
        CommandResult actual = command.execute(taskList);
        String response = MESSAGE_FIND + "\t 1." + matched.toString();
        CommandResult expected = new CommandResult(response, false);
        assertEquals(expected, actual);
    }

}
