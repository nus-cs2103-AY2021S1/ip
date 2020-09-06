package duke.command;

import duke.storage.Storage;
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
        int noOfTask = tasks.getNumberOfTask() - 1;
        String responseMessage = "Noted. I've removed this task:\n\t   "
                + tasks.getTask(taskNumber) + "\n\t "
                + "Now you have "
                + getTaskDescription(noOfTask)
                + " in the list.";
        boolean shouldExit = getIsExit();
        assert !shouldExit : "shouldExit should be false";
        tasks.removeTask(taskNumber);
        storage.save(tasks);
        return new CommandResponse(responseMessage, shouldExit);
    }

    /**
     * Returns a String description of the number of Task.
     *
     * @param noOfTask Number of Task in TaskList
     * @return String description of the number of Task.
     */
    public static String getTaskDescription(int noOfTask) {
        String taskDescription = "";
        if (noOfTask > 1) {
            taskDescription = noOfTask + " tasks";
        } else {
            taskDescription = noOfTask + " task";
        }
        return taskDescription;
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
