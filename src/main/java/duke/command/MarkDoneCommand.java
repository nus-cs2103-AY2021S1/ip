package duke.command;

import duke.ActionType;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command to mark a task as done
 */
public class MarkDoneCommand extends Command {
    private final int taskIndex;
    /**
     * MarkDoneCommand constructor
     *
     * @param taskIndex Index of task to be completed
     */
    public MarkDoneCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    /**
     * Marks task as done, then prints info
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     * @throws DukeException if exception encountered
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException("duke.tasks.Task does not exist _(´ཀ`」 ∠)_");
        } else {
        Task completedTask = tasks.markDone(taskIndex);
        storage.updateFile(tasks);
        ui.printTask(completedTask, ActionType.MARK_DONE);
        }
    }
}
