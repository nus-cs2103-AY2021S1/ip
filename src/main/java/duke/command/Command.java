package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    /**
     * Executes the given command.
     * 
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     */
    public abstract void executeCommand(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns an indicator if the program will terminate.
     * 
     * @return Indicator of termination.
     */
    public boolean isExit() {
        return false;
    }
}
