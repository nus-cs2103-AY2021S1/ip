package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import duke.action.Action;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class DeleteCommandTest {

    @Test
    public void invalidTaskIndex_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            Queue<Action> actionQueue = new LinkedList<>();
            tasks.add(new Todo("have fun"));
            int taskNumber = 7;
            Command newDeleteCommand = new DeleteCommand(taskNumber);
            newDeleteCommand.execute(new Ui(), new Storage("./data/duke.txt"), tasks, actionQueue);
        } catch (DukeException e) {
            assertEquals(">_< OOPS!!! \n" + "Task does not exist/invalid task number.", e.toString());
        }
    }
}
