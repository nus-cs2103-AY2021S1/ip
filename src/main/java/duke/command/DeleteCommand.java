package duke.command;

import duke.exception.InvalidDeleteIndexException;
import duke.util.Storage;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Responsible for executing a delete command.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand (int index) {
        super(true);
        this.index = index;
    }

    /**
     * Executes a delete command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @throws InvalidDeleteIndexException If index is out of bounds.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDeleteIndexException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidDeleteIndexException(tasks.size());
        }

        Task task = tasks.remove(index-1);
        storage.save(tasks);
        String response = String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", task, tasks.size());
        ui.printResponse(response);
    }

    @Override
    public String toString() {
        return "delete <task index>";
    }
}
