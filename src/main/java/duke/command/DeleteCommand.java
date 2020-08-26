package duke.command;

import duke.main.TaskList;
import duke.task.Task;

/**
 * DeleteCommand is a Command to delete a Task from the related TaskList.
 */
public class DeleteCommand extends Command{

    /** The index of the Task that wants to be deleted from the TaskList. */
    protected int taskNumber;

    /**
     * Constructs a DeleteCommand.
     *
     * @param taskNumber Index of Task that wants to be deleted.
     */
    public DeleteCommand(String taskNumber) {
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    /**
     * Deletes a Task from the related TaskList based on the given index.
     *
     * @param tasks The related TaskList.
     */
    @Override
    public void perform(TaskList tasks) {
        Task task = tasks.get(taskNumber - 1);
        tasks.delete(taskNumber - 1);
        System.out.println(" Okie! I have deleted this task: " + "\n"
                + "   " + task.toString() + "\n" + " Now you have " + tasks.size() + (tasks.size() > 1
                ? " tasks." : " task."));
    }

    /**
     * Checks if this is a termination Command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
