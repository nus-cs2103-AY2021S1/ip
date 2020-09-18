package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Creates a delete command.
 */
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx - 1;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The list of existing tasks.
     * @param ui      The ui that handles user interaction.
     * @param storage The storage that stores the list of existing tasks.
     * @throws DukeException when the command cannot be executed.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task deletedTask = tasks.get(idx);
            tasks.delete(idx);
            storage.writeFile(tasks);
            return ui.showDeleteMessage(deletedTask, tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number does not exist!");
        }
    }

    /**
     * Determines if the command is an exit command.
     *
     * @return Always false.
     */
    public boolean isExit() {
        return false;
    }
}
