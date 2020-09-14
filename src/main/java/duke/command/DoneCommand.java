package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task with index in the TaskList as done.
     *
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task = tasks.done(index);
        return ("Marked this task as done:\n" + task);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DoneCommand;
    }
}
