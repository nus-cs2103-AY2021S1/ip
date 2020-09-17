package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyListException;
import duke.exceptions.InvalidTaskNumberException;
import duke.logic.TaskList;
import duke.logic.tasks.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class UpdateCommand extends Command {
    private int taskNum;
    private String taskName;
    private String taskDate;

    /**
     * Instantiates an UpdateCommand object.
     * @param taskNum The task number to be updated.
     * @param taskName The new name of the task to be updated.
     * @param taskDate The new date and time of the task to be updated.
     */
    public UpdateCommand(int taskNum, String taskName, String taskDate) {
        this.taskNum = taskNum;
        this.taskName = taskName;
        this.taskDate = taskDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null && ui != null && storage != null
                : "TaskList, Ui or Storage is not supposed to be null";

        if (tasks.getSize() == 0) {
            throw new EmptyListException();
        }

        if (this.taskNum == 0 || this.taskNum > tasks.getSize()) {
            throw new InvalidTaskNumberException();
        }

        Task t = tasks.getTask(this.taskNum);
        updateTask(t);
        storage.updateData(tasks);
        return ui.showUpdate(t);
    }

    /**
     * Updates the task with the new task name or date and time.
     * @param t Task to be updated.
     * @throws DukeException If the date and time was not specified in the correct format.
     */
    public void updateTask(Task t) throws DukeException {
        if (this.taskName != null) {
            t.setTaskName(this.taskName);
        } else {
            t.setTaskDateTime(this.taskDate);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
