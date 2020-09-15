package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to add new Task.
 */
public class AddTaskCommand extends Command {

    /** The Task to be added. */
    private Task task;

    /**
     * Constructs a <code>AddTaskCommand</code> object.
     *
     * @param task Task to be added.
     */
    public AddTaskCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Adds a Task to the TaskList and notify the user if successful.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @return CommandResponse A response to the user.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks);
        String responseMessage = "Got it. I've added this task: \n\t   "
                + task + "\n "
                + "Now you have "
                + tasks.getNumberOfTaskDescription()
                + " in the list.";
        boolean shouldExit = getIsExit();
        assert !shouldExit : "shouldExit should be false";
        return new CommandResponse(responseMessage, shouldExit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof AddTaskCommand) {
            AddTaskCommand c = (AddTaskCommand) obj;
            return c.task.equals(task) && c.getIsExit() == this.getIsExit();
        } else {
            return false;
        }
    }
}

