package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import task.Task;


/**
 * Represents a DeleteCommand for deleting existing tasks.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of a DeleteCommand.
     *
     * @param taskIndex The index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task at the specified index from the TaskList.
     *
     * @param mainTasks The TaskList which stores unarchived tasks.
     * @param archivedTasks The TaskList which stores archived tasks.
     * @param storage The Storage which will delete the task at the location specified in its path.
     * @throws DukeException Thrown when task index invalid or relayed from Storage when removing task.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList mainTasks, TaskList archivedTasks, Storage storage) throws DukeException {
        boolean hasNegativeIndex = this.taskIndex < 0;
        boolean hasIndexOutOfBounds = this.taskIndex > mainTasks.size() - 1;
        boolean hasInvalidIndex = hasNegativeIndex || hasIndexOutOfBounds;

        if (hasInvalidIndex) {
            throw new DukeException("There is no such task.");
        }

        Task toDelete = mainTasks.get(this.taskIndex);
        assert toDelete != null : "toDelete should not be null";
        mainTasks.remove(this.taskIndex);
        storage.overwrite(mainTasks, false);

        return " Noted. I've removed this task:\n"
                + "   " + toDelete + "\n"
                + " Now you have " + mainTasks.size() + " tasks in the list.\n";
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on after removing a task.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
