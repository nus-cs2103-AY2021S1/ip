package duke.command;

import duke.main.TaskList;
import duke.task.Task;

/**
 * DoneCommand is a Command to mark as done a Task in the related TaskList based on the given index.
 */
public class DoneCommand extends Command {

    /** The index of the Task that wants to be marked as done. */
    protected int taskNumber;

    /**
     * Constructs a DoneCommand.
     *
     * @param taskNumber The index of the Task that wants to be marked as done.
     */
    public DoneCommand(String taskNumber) {
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    /**
     * Marks as done the Task in the TaskList based on the given index.
     *
     * @param tasks The related TaskList.
     */
    @Override
    public void perform(TaskList tasks) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        System.out.println(" Yay! I have marked this task as done: " + "\n"
                + "   " + task.toString());
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
