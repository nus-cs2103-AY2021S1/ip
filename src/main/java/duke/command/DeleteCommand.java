package duke.command;

import duke.ActionType;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command for deleting tasks
 */
public class DeleteCommand extends Command {
    private final int taskIndex;
    /**
     * DeleteCommand constructor
     *
     * @param taskIndex Index of class to be deleted
     */
    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes task from list and returns its info
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     * @return Info on deleted task
     * @throws DukeException if exception encountered
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= tasks.getList().size()) {
            throw new DukeException("Task does not exist _(´ཀ`」 ∠)_");
        } else {
            Task deletedTask = tasks.delete(taskIndex);
            storage.updateFile(tasks);
            return ui.printTask(deletedTask, ActionType.MARK_DONE);
        }
    }
}
