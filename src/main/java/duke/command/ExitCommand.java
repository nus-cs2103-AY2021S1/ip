package duke.command;

import duke.storage.Storage;

import duke.tasklist.TaskList;

import duke.ui.Ui;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Terminates the program.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
    }

    /**
     * Returns true to terminate the program.
     *
     * @return True
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
