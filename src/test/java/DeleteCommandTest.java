import duke.TaskList;
import duke.Ui;
import duke.command.DeleteCommand;
import duke.exception.InvalidTaskIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void deleteFirstTaskTest() {
        DeleteCommand deleteCommand = new DeleteCommand();
        TaskList taskList = new TaskList();
        Ui ui = new Ui(taskList);
        String input = "delete 1";

        int before = taskList.taskSize();

        try {
            deleteCommand.execute(taskList, ui, input);
        } catch (InvalidTaskIdException e) {
            System.out.println(e);
        }

        int after = taskList.taskSize();

        if (before == 0) {
            assertEquals(before, after);
        } else {
            assertEquals(before - 1, after);
        }
    }
}
