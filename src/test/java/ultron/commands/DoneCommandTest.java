package ultron.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ultron.Storage;
import ultron.TaskList;
import ultron.exceptions.UltronException;
import ultron.ui.UI;

public class DoneCommandTest {
    /**
     * Assert if Done Command isExit is false.
     */
    @Test
    public void isExitTest() {
        DoneCommand command = new DoneCommand("");
        assertFalse(command.isExit());
    }

    /**
     * Check if the command can be executed with invalid arguments.
     */
    @Test
    public void invalidArgumentTest() {
        DoneCommand command = new DoneCommand("not suppose to be here");
        TaskList taskList = new TaskList(new ArrayList<>());
        UI ui = new UI();
        Storage storage = new Storage("test.txt");
        try {
            command.execute(taskList, ui, storage);
            assert false;
        } catch (UltronException e) {
            assert true;
        }
    }
}
