package duke.command;

import duke.exception.InvalidTaskNumberException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to mark a Task from TaskList as done.
 */
public class DoneCommand extends Command {

    /** Index of Task to be marked as done */
    private int taskNumber;

    /**
     * Constructs a <code>DoneCommand</code> object.
     *
     * @param taskNumber Index of Task to be marked as done.
     */
    public DoneCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
        assert taskNumber >= 0 : "taskNumber should not be less than 0";
    }

    /**
     * Marks a Task from the TaskList as done and notify the user.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @return CommandResponse A response to the user.
     * @throws InvalidTaskNumberException If taskNumber is not in the range of the 0 to size of tasks.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskNumberException {
        tasks.doTask(taskNumber);
        storage.save(tasks);
        String responseMessage = "Nice! I've marked this task as done:\n\t   "
                + tasks.getTask(taskNumber);
        boolean shouldExit = getIsExit();
        assert !shouldExit : "shouldExit should be false";
        return new CommandResponse(responseMessage, shouldExit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DoneCommand) {
            DoneCommand c = (DoneCommand) obj;
            return c.taskNumber == this.taskNumber && c.getIsExit() == this.getIsExit();
        } else {
            return false;
        }
    }

}
