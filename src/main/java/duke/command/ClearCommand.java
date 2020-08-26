package duke.command;

import duke.*;
import duke.exception.DukeLoadingErrorException;

/**
 * Represents a command to clear the task list.
 */
public class ClearCommand extends Command {

    /**
     * Class constructor.
     */
    public ClearCommand() {
        super("clear");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeLoadingErrorException {
        tasks.clearTasks();
        ui.printClearTasksWindow();
        storage.save(tasks.getTasks());
    }

}
