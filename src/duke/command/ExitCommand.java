package duke.command;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * This class represents the exit command.
 * When executed, duke program will be terminated.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand. Executing this command
     * will stop the duke from running.
     *
     * @param tasks TaskList of the current task.
     * @param ui Ui to deals with interactions with the user.
     * @param storage Storage to save the data to the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.exit();
    }
}
