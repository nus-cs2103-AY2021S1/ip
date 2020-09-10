package duke.logic.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    /**
     * Shows a help message to users to show the possible commands that can be called.
     * @param tasks duke.tasks.Task list of all tasks.
     * @param ui duke.ui.Ui to deal with interaction with user.
     * @param storage duke.storage.Storage to load and save tasks.
     * @return A help message with list of commands and description.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasklist not found.";
        assert ui != null : "duke.ui.Ui not found.";
        assert storage != null : "duke.storage.Storage not found.";

        assert ui.showHelp() != null : "Message showing possible commands should be shown.";
        return ui.showHelp();
    }

    public boolean isExit() {
        return false;
    }
}
