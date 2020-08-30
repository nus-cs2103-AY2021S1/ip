package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeLoadingErrorException {
        tasks.clearTasks();
        storage.save(tasks.getTasks());
        return ui.printClearTasksWindow();
    }

}
