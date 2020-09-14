package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommandTest {

    private final Storage storage = new Storage("data", "data/dukeTest.txt");
    private TaskList taskList;
    private final Ui ui = new Ui();

    {
        try {
            taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeTest() {
        AddCommand addCommand = new AddCommand("todo", " ");
        try {
            addCommand.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Description cannot be only empty spaces!", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        addCommand = new AddCommand("deadline", " ");
        try {
            addCommand.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Description cannot be only empty spaces!", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        addCommand = new AddCommand("deadline", "homework /by 01/40/2020 1900");
        try {
            addCommand.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Please enter dates in this format: dd/MM/yyyy HHmm"
                    + "\nE.g. 01/12/2020 2359", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        addCommand = new AddCommand("deadline", "NotMakingSense");
        try {
            addCommand.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Invalid description!", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        addCommand = new AddCommand("event", " ");
        try {
            addCommand.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Description cannot be only empty spaces!", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        addCommand = new AddCommand("event", "eat /at 01/40/2020 1900");
        try {
            addCommand.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Please enter dates in this format: dd/MM/yyyy HHmm"
                    + "\nE.g. 01/12/2020 2359", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        addCommand = new AddCommand("event", "NotMakingSense");
        try {
            addCommand.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Invalid description!", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isExitTest() {
        assertFalse(new AddCommand("todo", "sample").isExit());
        assertFalse(new AddCommand("deadline", "sample "
                + "/by 20/10/2020 1900").isExit());
        assertFalse(new AddCommand("event", "sample "
                + "/at 20/10/2020 1900").isExit());
    }

}

