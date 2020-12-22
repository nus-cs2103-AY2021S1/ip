package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.MarkedDoneException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a task as done and updates it in both the task list and file in filepath specified in storage.
     *
     * @param tasks duke.tasks.Task list of all tasks.
     * @param ui duke.ui.Ui to deal with interaction with user.
     * @param storage duke.storage.Storage to load and save tasks.
     * @return The string output message showing task that is marked done.
     * @throws DukeException If task number is invalid or task is already marked done.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "Tasklist not found.";
        assert ui != null : "duke.ui.Ui not found.";
        assert storage != null : "duke.storage.Storage not found.";

        // check if the taskNumber provided is in range
        if (taskNumber > tasks.getNumTasks() || taskNumber <= 0) {
            throw new InvalidTaskException();
        } else if (tasks.getTask(taskNumber).getIsDone()) {
            throw new MarkedDoneException();
        }

        tasks.doneTask(taskNumber);
        storage.overwriteFile(tasks.getTaskList());
        assert ui.showMarkedDone(tasks.getTask(taskNumber)) != null : "Message to show task is marked should be shown.";
        return ui.showMarkedDone(tasks.getTask(taskNumber));
    }

    public boolean isExit() {
        return false;
    }
}
