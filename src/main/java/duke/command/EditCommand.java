package duke.command;

import duke.ActionType;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command to edit a task
 */
public class EditCommand extends Command {
    private final int taskIndex;
    private final String editInput;
    /**
     * EditCommand constructor
     *
     * @param taskIndex Index of task to be edited
     */
    public EditCommand(int taskIndex, String editInput) {
        super(false);
        this.taskIndex = taskIndex;
        this.editInput = editInput;
    }

    /**
     * Edits task, then prints its info
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     * @return Info on edited task
     * @throws DukeException if exception encountered
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException("Task does not exist _(´ཀ`」 ∠)_");
        } else {
            Task editedTask = tasks.edit(taskIndex, editInput);
            storage.updateFile(tasks);
            return ui.printTask(editedTask, ActionType.EDIT);
        }
    }
}
