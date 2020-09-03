package duke.command;

import duke.exception.InvalidDeleteIndexException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Responsible for executing a delete command.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand (int index) {
        super(true);
        this.index = index;
    }

    /**
     * Executes a delete command and returns a response.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @throws InvalidDeleteIndexException If index is out of bounds.
     * @return Message when the command is completed.
     */
    @Override
    public String executeWithResponse(TaskList tasks, Ui ui, Storage storage)
            throws InvalidDeleteIndexException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidDeleteIndexException(tasks.size());
        }

        int previousTaskSize = tasks.size();
        Task task = tasks.remove(index - 1);
        int subsequentTaskSize = tasks.size();
        assert (previousTaskSize == subsequentTaskSize - 1);
        storage.save(tasks);
        return String.format("Noted. I've removed this task:\n"
                + "%s\n" + "Now you have %d tasks in the list.", task, tasks.size());
    }

    @Override
    public String toString() {
        return "delete <task index>";
    }
}
