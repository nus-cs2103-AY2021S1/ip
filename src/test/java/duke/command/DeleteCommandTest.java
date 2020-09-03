package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class DeleteCommandTest {
    @Test
    public void isExitTrue_deleteCommandCreated_false() {
        Command test = new DeleteCommand("delete 3");
        assertFalse(test.isExit());
    }

    @Test
    public void isTaskDeleted_deleteTask_exceptionThrown() {
        Command test = new DeleteCommand("delete 3");
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
