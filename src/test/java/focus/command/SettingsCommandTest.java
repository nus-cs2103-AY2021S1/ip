package focus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import focus.exception.FocusException;
import focus.storage.Storage;
import focus.task.TaskList;

public class SettingsCommandTest {
    private final SettingsCommand settingsCommand = new SettingsCommand();
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testIsExit() {
        assertFalse(settingsCommand.isExit());
    }

    @Test
    public void testExecute() {
        try {
            settingsCommand.execute("settings", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Please type in the correct format for settings!\n"
                    + "\tIf you need an example, type 'help'!", e.getMessage());
        }
        String message = "";
        try {
            message = settingsCommand.execute("settings /days 4", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tThe period of reminders has been changed to: 4 days!",
                    message);
        }
    }
}
