package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to delete task.
 */
public class DeleteCommand extends Command {
    private int taskNo;

    /**
     * Constructor for DeleteCommand.
     * @param taskNo The task number of the task to be deleted.
     */
    public DeleteCommand(int taskNo) {
        super("delete");
        this.taskNo = taskNo;
    }

    /**
     * Executes the command to delete a task.
     * @param tasks The task list that is involved.
     * @param ui The UI of Duke.
     * @param storage The storage of Duke.
     * @throws DukeException If there is the task number does not exist.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.deleteTask(taskNo);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
