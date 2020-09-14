package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task with index from the TaskList.
     *
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task = tasks.delete(index);
        return ("Deleted this task:\n" + task);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeleteCommand;
    }
}
