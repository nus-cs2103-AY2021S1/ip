package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommandTest {

    @Test
    public void executeToStringTest() {
        String userInput = "done 1";
        Command c = new DoneCommand(userInput);

        ArrayList<Task> taskArray = new ArrayList<>();
        TaskList tasks = new TaskList(taskArray);
        tasks.add(new Task("todo do this"));
        Ui ui = new Ui();
        Storage storage = new Storage("/");

        try {
            c.executeToString(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        assertEquals(tasks.get(tasks.size() - 1).isDone(), true);

    }
}
