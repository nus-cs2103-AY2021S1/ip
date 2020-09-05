package focus;

import focus.command.Command;
import focus.exception.FocusException;
import focus.parser.Parser;
import focus.storage.Storage;
import focus.task.TaskList;
import focus.ui.UI;

/** Represents Focus. */
public class Focus {
    /** Storage created for user. */
    private final Storage storage;
    /** Task list created for user. */
    private final TaskList taskList;
    /** UI created to interact with user. */
    private final UI ui;

    /** Creates Focus to set up the things needed. */
    public Focus() {
        ui = new UI();
        Storage.createFolder();
        storage = new Storage();
        if (storage.retrieveFocusTextFile()) {
            taskList = new TaskList(storage.loadData());
        } else {
            taskList = new TaskList();
        }
        if (storage.retrieveSettingsTextFile()) {
            storage.loadSettings();
        }
    }

    /**
     * Gets response from Pocus based on user's input.
     *
     * @param input User's input.
     * @return Pocus' response.
     */
    public String getResponse(String input) {
        boolean isExit;
        try {
            Command command = Parser.parse(input);
            isExit = command.isExit();
            if (isExit) {
                return ui.exitFocus();
            }
            return command.execute(input, taskList, storage);
        } catch (FocusException e) {
            return e.getMessage();
        }
    }
}
