package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to exit duke
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit duke.
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     * @return Output strings displayed on the UI showing exit
     */
    @Override
    public String[] execute(Storage storage, TaskList tasks, Ui ui) {
        storage.resetFile();
        storage.saveTaskList(tasks);
        return ui.getExitStrings();
    }
}
