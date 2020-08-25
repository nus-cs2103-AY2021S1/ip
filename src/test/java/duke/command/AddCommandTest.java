package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void executeTest() {
        String userInput = "todo do something";
        String filePath = "/";

        Command c = new AddCommand(userInput);

        ArrayList<Task> taskArray = new ArrayList<>();

        TaskList tasks =  new TaskList(taskArray);
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);

        try {
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        assertEquals(tasks.get(tasks.size() - 1), new Task(userInput));
    }
}
