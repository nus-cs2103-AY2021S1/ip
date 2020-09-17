package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Creates a done command.
 */
public class DoneCommand extends Command {
    private int idx;

    public DoneCommand(int idx) {
        this.idx = idx - 1;
    }

    /**
     * Executes the command.
     *
     * @param tasks The list of existing tasks.
     * @param ui The ui that handles user interaction.
     * @param storage The storage that stores the list of existing tasks.
     * @throws DukeException when the command cannot be executed.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (tasks.size() == 0) {
                throw new IndexOutOfBoundsException();
            }
            Task doneTask = tasks.get(idx);
            doneTask.markDone();
            ui.showDoneMessage(doneTask);
            storage.writeFile(tasks);
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
