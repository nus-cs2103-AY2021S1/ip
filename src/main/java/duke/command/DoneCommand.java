package duke.command;

import duke.main.TaskList;
import duke.task.Task;

/**
 * DoneCommand is a Command to mark as done a Task in the related TaskList based on the given index.
 */
public class DoneCommand extends Command {

    /** The index of the Task that wants to be marked as done. */
    protected int taskNumber;
    protected TaskList tasks;
    protected Task task;

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
     * @param t The related TaskList.
     */
    @Override
    public void perform(TaskList t) {
        tasks = t;
        task = tasks.get(taskNumber - 1);
        task.markAsDone();
    }

    public String getReply() {
        return " Yay! I have marked this task as done: " + "\n"
            + "   " + task.toString();
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
