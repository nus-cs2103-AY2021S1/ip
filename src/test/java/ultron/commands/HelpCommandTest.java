package ultron.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ultron.Storage;
import ultron.TaskList;
import ultron.exceptions.UltronException;
import ultron.ui.UI;

public class HelpCommandTest {
    /**
     * Check if isExit is correct for help command.
     */
    @Test
    public void isExitTest() {
        HelpCommand helpCommand = new HelpCommand("");
        assertFalse(helpCommand.isExit());
    }
    /**
     * Check if the message displayed is correct for help command.
     */
    @Test
    public void executeTest() {
        HelpCommand helpCommand = new HelpCommand("");

        TaskList taskList = new TaskList(new ArrayList<>());
        UI ui = new UI();
        Storage storage = new Storage("test.txt");

        try {
            helpCommand.execute(taskList, ui, storage);
            assertEquals("Heh I guess I could help an insect like you:\n"
                + "- help                      : Get help for the commands\n"
                + "- todo (name)               : Adds a todo to the list\n"
                + "- event (name) /at (date)   : Adds an event at date\n"
                + "- deadline (name) /by (date): "
                + "Adds a deadline which expires by date\n"
                + "- delete (number)           : Removes a todo from the list\n",
                ui.getMessage());
        } catch (UltronException e) {
            assert false;
        }
    }
    /**
     * Check if the help message throws an error when an invalid argument is added.
     */
    @Test
    public void executeErrorTest() {
        HelpCommand helpCommand = new HelpCommand("error argument");

        TaskList taskList = new TaskList(new ArrayList<>());
        UI ui = new UI();
        Storage storage = new Storage("test.txt");

        try {
            helpCommand.execute(taskList, ui, storage);
            assert false;
        } catch (UltronException e) {
            assert true;
        }
    }
}
