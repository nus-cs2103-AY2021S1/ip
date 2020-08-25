package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeleteCommandTest {
    @Test
    public void executeTest() {
        String userInput = "delete 1";
        Command c = new DeleteCommand(userInput);
        ArrayList<Task> taskArray = new ArrayList<>();

        TaskList tasks =  new TaskList(taskArray);
        tasks.add(new Task("todo do this"));
        tasks.add(new Task("todo do that"));
        Ui ui = new Ui();
        Storage storage = new Storage("/");

        try {
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        assertEquals(tasks.size(), 1);
    }
}
