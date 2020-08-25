package duke.command;

import duke.exception.InvalidDoneIndexException;
import duke.util.Storage;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Responsible for a done command.
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        super(true);
        this.index = index;
    }

    /**
     * Executes a done command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @throws InvalidDoneIndexException If index is out of bounds.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDoneIndexException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidDoneIndexException(tasks.size());
        }
        
        Task task = tasks.get(index-1);
        task.completeTask();
        storage.save(tasks);
        String response = "Nice! I've marked this task as done:\n  " + task.toString();
        ui.printResponse(response);
    }

    @Override
    public String toString() {
        return "done <task index>";
    }
}
