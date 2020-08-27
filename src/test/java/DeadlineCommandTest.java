import duke.TaskList;
import duke.Ui;
import duke.command.DeadlineCommand;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIdException;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    public void deadlineDateParsingTest() {

        DeadlineCommand deadlineCommand = new DeadlineCommand();
        TaskList taskList = new TaskList();
        Ui ui = new Ui(taskList);
        String input = "deadline Laundry /by 2020-12-30";
        try {
            deadlineCommand.execute(taskList, ui, input);
        } catch (InvalidTaskIdException | EmptyDescriptionException | InvalidFormatException e) {
            e.printStackTrace();
        }
        Task lastTaskAdded = taskList.getTasks().get(taskList.taskSize() - 1);
        assertEquals(lastTaskAdded.toString(), "[✗][D] Laundry (by: Dec 30 2020)");
    }
}
