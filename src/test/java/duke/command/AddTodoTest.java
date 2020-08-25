package duke.command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddTodoTest {
    
    @Test
    public void testExecute_exceptionEmptyThrown() {
        String[] command = {"todo"};
        TodoCommand test = new TodoCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("test.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            Assertions.assertEquals("The description of a todo cannot be empty~", e.toString());
        }
    }
}
