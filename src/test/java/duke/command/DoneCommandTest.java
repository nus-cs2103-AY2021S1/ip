package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class DoneCommandTest {
    @Test
    public void isExitTrue_doneCommandCreated_false() {
        Command test = new DoneCommand("done 3");
        assertFalse(test.isExit());
    }

    @Test
    public void isTaskDone_markTaskAsDone_exceptionThrown() {
        Command test = new DoneCommand("done 3");
        Path storageFilePath = Paths.get(".", "data", "test.txt");
        TaskList l = new TaskList();
        Storage s = new Storage(storageFilePath);
        try {
            test.execute(l, s);
        } catch (DukeException e) {
            assertTrue(e.getMessage().contains("Please enter a valid task number"));
        }
    }
}
