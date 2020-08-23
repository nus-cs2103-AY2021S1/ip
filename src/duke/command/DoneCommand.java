package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;


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
     * @param ui The Ui which will generate outputs significant to the user.
     * @param storage The Storage which will update the task at the location specified in its path.
     * @throws DukeException Thrown when task index invalid or relayed from Storage when updating task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskIndex < 0 || this.taskIndex > tasks.size() - 1) {
            throw new DukeException("\tThere is no such task.");
        }
        Task toChange = tasks.get(this.taskIndex);
        toChange.markAsDone();
        storage.overwrite(tasks);
        String output = "\t Nice! I've marked this task as done:\n" +
                "\t  " + toChange + "\n";
        ui.showMessage(output);
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
