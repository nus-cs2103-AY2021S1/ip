package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import task.Task;


/**
 * Represents a DoneCommand for marking existing tasks as done.
 */
public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of a DoneCommand.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task at the specified index in the TaskList as done.
     *
     * @param mainTasks The TaskList which stores unarchived tasks.
     * @param archivedTasks The TaskList which stores archived tasks.
     * @param storage The Storage which will update the task at the location specified in its path.
     * @throws DukeException Thrown when task index invalid or relayed from Storage when updating task.
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

        Task toChange = mainTasks.get(this.taskIndex);
        assert toChange != null : "toChange should not be null";
        toChange.markAsDone();
        storage.overwrite(mainTasks, false);

        return " Nice! I've marked this task as done:\n"
                + "  " + toChange + "\n";
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on after marking a task as done.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
