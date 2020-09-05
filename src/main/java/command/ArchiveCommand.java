package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import task.Task;

public class ArchiveCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of a ArchiveCommand.
     *
     * @param taskIndex The index of the task to archive.
     */
    public ArchiveCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Archives the task at the specified index from the unarchivedTasks TaskList.
     *
     * @param mainTasks The TaskList which stores unarchived tasks.
     * @param archivedTasks The TaskList which stores archived tasks.
     * @param storage The Storage which will archive the task at the location specified in its path.
     * @throws DukeException Thrown when task index invalid or relayed from Storage when removing task.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList mainTasks, TaskList archivedTasks, Storage storage) throws DukeException {
        boolean hasNegativeIndex = this.taskIndex < 0;
        boolean hasIndexOutOfBounds = this.taskIndex > mainTasks.size() - 1;
        boolean hasInvalidIndex = hasNegativeIndex || hasIndexOutOfBounds;

        if (hasInvalidIndex) {
            throw new DukeException("\tThere is no such task.");
        }

        Task toArchive = mainTasks.get(this.taskIndex);
        assert toArchive != null : "toDelete should not be null";

        mainTasks.remove(this.taskIndex);
        storage.overwrite(mainTasks, false);

        archivedTasks.add(toArchive);
        storage.overwrite(archivedTasks, true);

        return "\t Noted. I've archived this task:\n"
                + "\t   " + toArchive + "\n"
                + "\t Now you have " + mainTasks.size() + " tasks in the list.\n";
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on after archiving a task.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
