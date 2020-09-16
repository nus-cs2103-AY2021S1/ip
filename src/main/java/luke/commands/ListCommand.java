package luke.commands;


import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;

/**
 * Represents a command to list out current tasks.
 */
public class ListCommand extends Command {

    protected TaskList tasks;

    /**
     * Creates a ListCommand to list out current tasks.
     */
    public ListCommand() {
        super();
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.showListResult(tasks);
    }
}
