package nite.command;

import nite.storage.Storage;
import nite.task.TaskList;
import nite.ui.Ui;

/**
 * Represents a ExitCommand where user wants to exit from duke.Duke.
 */
public class ExitCommand extends Command {

    /**
     * Creates an ExitCommand.
     */
    public ExitCommand() {
    }

    /**
     * Executes the command to exit from duke.Duke program.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @return String displaying completion of Command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        return ui.showFarewell();
    }

    /**
     * Returns whether a Command is an ExitCommand.
     *
     * @return True as this is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
