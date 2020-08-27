package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    @Test
    public void invalidTaskIndex_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("have fun"));
            int taskNumber = 7;
            Command newDeleteCommand = new DeleteCommand(taskNumber);
            newDeleteCommand.execute(tasks, new Ui(), new Storage("./data/duke.txt"));
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Task does not exist/invalid task number.", e.toString());
        }
    }
}
