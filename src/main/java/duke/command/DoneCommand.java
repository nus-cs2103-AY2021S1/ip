package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;
import duke.exceptions.NoSuchTaskException;

/**
 * Command to mark a Task as complete. Created by using "done taskNumber"
 */
public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Mark the Task with the specified taskNumber as completed.
     *
     * @param tasks task list containing all tasks
     * @param ui ui for interaction with user
     * @param storage storage to retrieve and store tasks entered by user
     * @throws NoSuchTaskException if invalid taskNumber was provided
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskException {
        Task completedTask = tasks.completeTask(taskNumber);
        ui.printMessage(String.format("Great! I've marked the following task as done: \n %s", completedTask));
        storage.updateTasks(tasks.getListOfTasks());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DoneCommand) {
            return this.taskNumber == ((DoneCommand) other).taskNumber;
        } else {
            return false;
        }
    }
}
