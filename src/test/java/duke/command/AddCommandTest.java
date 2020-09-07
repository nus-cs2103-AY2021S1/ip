package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommandTest {
    @Test
    public void executeToStringTest() {
        String userInput = "todo do something";
        String filePath = "/";
        String actual = "";

        Command c = new AddCommand(userInput);

        ArrayList<Task> taskArray = new ArrayList<>();

        TaskList tasks = new TaskList(taskArray);
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);

        try {
            actual = c.executeToString(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        String expected = "    Got it. I've added this task:\n"
                + "        " + new ToDo(userInput) + '\n'
                + "    Now you have " + tasks.size() + " tasks in the list.";

        assertEquals(actual, expected);
    }
}
