package duke.logic.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to terminate the duke.Duke chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Executes command to display the goodbye message.
     *
     * @param tasks duke.tasks.Task list of all tasks.
     * @param ui duke.ui.Ui to deal with interaction with user.
     * @param storage duke.storage.Storage to load and save tasks.
     * @return Goodbye message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasklist not found.";
        assert ui != null : "duke.ui.Ui not found.";
        assert storage != null : "duke.storage.Storage not found.";

        assert ui.showBye() != null : "Message showing bye should be shown.";
        return ui.showBye();
    }

    public boolean isExit() {
        return true;
    }
}
