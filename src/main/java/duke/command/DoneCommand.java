package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
     * @return the execution message.
     * @throws DukeException if the task doesn't exist.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        boolean isOutOfBounds = this.idx >= tasks.getSize() || this.idx < 0;

        if (isOutOfBounds) {
            throw new DukeException("Oh dear! That task doesn't exist!");
        }

        Task doneTask = tasks.setAsDone(this.idx);
        storage.save(tasks.getList());
        return ui.printDoneMessage(doneTask);
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
