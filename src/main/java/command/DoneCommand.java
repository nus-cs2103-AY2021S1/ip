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
     * @param tasks The TaskList which contains the existing task to be mark as done.
     * @param storage The Storage which will update the task at the location specified in its path.
     * @throws DukeException Thrown when task index invalid or relayed from Storage when updating task.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (this.taskIndex < 0 || this.taskIndex > tasks.size() - 1) {
            throw new DukeException("\tThere is no such task.");
        }
        Task toChange = tasks.get(this.taskIndex);
        assert toChange != null : "toChange should not be null";
        toChange.markAsDone();
        storage.overwrite(tasks);

        return "\t Nice! I've marked this task as done:\n"
                + "\t  " + toChange + "\n";
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
