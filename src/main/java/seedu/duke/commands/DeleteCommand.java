package seedu.duke.commands;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.Storage;

/**
 * Represents the command to delete task.
 */
public class DeleteCommand extends Command {
    private int taskNo;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(taskNo);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
