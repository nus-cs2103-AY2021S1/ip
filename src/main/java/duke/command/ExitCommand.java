package duke.command;

import duke.Ui;
import duke.Storage;
import duke.tasks.TaskList;

/**
 * Command to quit Duke
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Exits program
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
        System.exit(0);
    }
}
