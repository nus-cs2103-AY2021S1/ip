package duke.command;

import duke.exception.InvalidDoneIndexException;
import duke.task.Task;
import duke.ui.Ui;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Responsible for a done command.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructs a DoneCommand.
     *
     * @param index The index of the task to mark as done.
     */
    public DoneCommand(int index) {
        super(true);
        this.index = index;
    }

    /**
     * Executes a done command and returns a response.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @throws InvalidDoneIndexException If index is out of bounds.
     * @return Message when the command is completed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidDoneIndexException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidDoneIndexException(tasks.size());
        }

        Task task = tasks.get(index - 1);
        task.completeTask();
        storage.save(tasks);
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    @Override
    public String toString() {
        return "done <task index>";
    }
}
