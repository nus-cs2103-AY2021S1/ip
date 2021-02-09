package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to list out all the task.
 */
public class ListCommand extends Command {

    /**
     * Constructor of ListCommand.
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Executes the command to list out tasks.
     * @param tasks The task list that is involved.
     * @param ui The UI of Duke.
     * @param storage The storage of Duke.
     * @throws DukeException If the task list is empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.listTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
