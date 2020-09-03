package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class ListCommandTest {
    @Test
    public void isExitTrue_listCommandCreated_false() {
        Command test = new ListCommand();
        assertFalse(test.isExit());
    }

    @Test
    public void isTaskDone_markTaskAsDone_exceptionThrown() {
        Command test = new ListCommand();
        Path storageFilePath = Paths.get(".", "data", "test.txt");
        TaskList l = new TaskList();
        Storage s = new Storage(storageFilePath);
        try {
            assertTrue(test.execute(l, s).contains("Here are all the items in your list"));
        } catch (DukeException e) {
            System.out.println("Test failed");
        }
    }
}
