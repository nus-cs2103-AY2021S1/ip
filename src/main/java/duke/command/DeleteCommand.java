package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to delete a Task from TaskList.
 */
public class DeleteCommand extends Command {

    /** Index of Task to be deleted */
    private final int taskNumber;

    /**
     * Constructs a <code>DeleteCommand</code> object.
     *
     * @param taskNumber Index of Task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
        assert taskNumber >= 0 : "taskNumber should not be less than 0";
    }

    /**
     * Deletes a Task from the TaskList and notify the user.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.getTask(taskNumber);
        tasks.removeTask(taskNumber);
        String responseMessage = "Noted. I've removed this task:\n\t   "
                + deletedTask + "\n\t "
                + "Now you have "
                + tasks.getNumberOfTaskDescription()
                + " in the list.";
        boolean shouldExit = getIsExit();
        assert !shouldExit : "shouldExit should be false";
        storage.save(tasks);
        return new CommandResponse(responseMessage, shouldExit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DeleteCommand) {
            DeleteCommand c = (DeleteCommand) obj;
            return c.taskNumber == this.taskNumber && c.getIsExit() == this.getIsExit();
        } else {
            return false;
        }
    }

}
