package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * Represents a done command in the Duke program.
 */
public class DoneCommand extends Command {

    private int idx;

    /**
     * Initializes a newly created done command with a task index.
     *
     * @param idx index of the task to set as done.
     */
    public DoneCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the done command.
     *
     * @param tasks TaskList of the program.
     * @param ui user interface of the program.
     * @param storage storage of the program.
     * @throws DukeException if the task doesn't exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.idx >= tasks.size()) {
            throw new DukeException("Oh dear! That task doesn't exist!");
        }
        Task doneTask = tasks.setDone(this.idx);
        storage.save(tasks.getList());
        ui.onDone(doneTask);
    }

    /**
     * Checks whether an object equals this done command.
     *
     * @param obj object to be compared with this command.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DoneCommand) {
            DoneCommand dc = (DoneCommand) obj;
            return this.idx == dc.idx;
        } else {
            return false;
        }
    }
}
