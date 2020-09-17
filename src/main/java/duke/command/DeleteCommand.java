package duke.command;

import duke.main.TaskList;
import duke.task.Task;

/**
 * DeleteCommand is a Command to delete a Task from the related TaskList.
 */
public class DeleteCommand extends Command {
    /** The index of the Task that wants to be marked as done. */
    private int taskNumber;
    /** TaskList that is related to this command. **/
    private TaskList tasks;
    /** Task that is related to this command. **/
    private Task task;

    /**
     * Constructs a DeleteCommand.
     *
     * @param taskNumber Index of Task that wants to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a Task from the related TaskList based on the given index.
     *
     * @param t The related TaskList.
     */
    @Override
    public void perform(TaskList t) {
        tasks = t;
        task = t.get(taskNumber);
        int a = tasks.getSize();
        tasks.delete(taskNumber);
        assert tasks.getSize() == a - 1;
    }

    /**
     * Gets the reply after performing the Command.
     *
     * @return A reply as a String based on the perform method.
     **/
    @Override
    public String getReply() {
        return " Okie! I have deleted this task: " + "\n"
            + "   " + task.toString() + "\n" + " Now you have " + tasks.getSize() + (tasks.getSize() > 1
            ? " tasks." : " task.");
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
