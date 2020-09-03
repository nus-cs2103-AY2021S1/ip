package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class AddCommandTest {
    @Test
    public void isExitTrue_addCommandCreated_false() {
        Command test = new AddCommand("todo walk dog", Commands.TODO);
        assertFalse(test.isExit());
    }


    @Test
    public void isAddingToDo_executeWithTodo_true() {
        Command test = new AddCommand("todo walk dog", Commands.TODO);
        Path storageFilePath = Paths.get(".", "data", "test.txt");
        TaskList l = new TaskList();
        Storage s = new Storage(storageFilePath);
        try {
            assertTrue(test.execute(l, s).contains("Got it. I've added this task"));
        } catch (DukeException e) {
            System.out.println("Test failed");

        }
    }
}
