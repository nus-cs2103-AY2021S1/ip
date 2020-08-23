package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;


/**
 * Represents a DeleteCommand for deleting existing tasks.
 */
public class DeleteCommand extends Command{
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
     * @param tasks The TaskList which contains the existing task.
     * @param ui The Ui which will generate outputs significant to the user.
     * @param storage The Storage which will delete the task at the location specified in its path.
     * @throws DukeException Thrown when task index invalid or relayed from Storage when removing task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskIndex < 0 || this.taskIndex > tasks.size() - 1) {
            throw new DukeException("\tThere is no such task.");
        }
        Task toDelete = tasks.get(this.taskIndex);
        tasks.remove(this.taskIndex);
        storage.overwrite(tasks);
        String output = "\t Noted. I've removed this task:\n" +
                "\t   " + toDelete + "\n" +
                "\t Now you have " + tasks.size() + " tasks in the list.\n";
        ui.showMessage(output);
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
