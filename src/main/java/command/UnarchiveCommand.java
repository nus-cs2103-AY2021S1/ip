package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import task.Task;

public class UnarchiveCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of a UnarchiveCommand.
     *
     * @param taskIndex The index of the task to unarchive.
     */
    public UnarchiveCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Unarchives the task at the specified index from the archivedTasks TaskList.
     *
     * @param mainTasks The TaskList which stores unarchived tasks.
     * @param archivedTasks The TaskList which stores archived tasks.
     * @param storage The Storage which will unarchive the task at the location specified in its path.
     * @throws DukeException Thrown when task index invalid or relayed from Storage when removing task.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList mainTasks, TaskList archivedTasks, Storage storage) throws DukeException {
        boolean hasNegativeIndex = this.taskIndex < 0;
        boolean hasIndexOutOfBounds = this.taskIndex > archivedTasks.size() - 1;
        boolean hasInvalidIndex = hasNegativeIndex || hasIndexOutOfBounds;

        if (hasInvalidIndex) {
            throw new DukeException("\tThere is no such task.");
        }

        Task toUnarchive = archivedTasks.get(this.taskIndex);
        assert toUnarchive != null : "toUnarchive should not be null";

        archivedTasks.remove(this.taskIndex);
        storage.overwrite(archivedTasks, true);

        mainTasks.add(toUnarchive);
        storage.overwrite(mainTasks, false);

        return "\t Noted. I've unarchived this task:\n"
                + "\t   " + toUnarchive + "\n"
                + "\t Now you have " + mainTasks.size() + " tasks in the list.\n";
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on after unarchiving a task.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
