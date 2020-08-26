package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the ExitCommand when users wants to exit the Program.
 */
public class ExitCommand extends Command {

    /**
     * Creates an ExitCommand.
     */
    public ExitCommand() { }

    /**
     * Exits the program.
     *
     * @param tasks taskList that stores Task objects
     * @param ui Ui that handles input and output to User
     * @param storage storage that handles data storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true if and only if it is a command to exit the program.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
