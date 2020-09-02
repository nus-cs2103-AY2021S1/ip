package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ListCommand extends Command {

    /**
     * Provides the user with a list of tasks in the TaskList.
     *
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String text = tasks.listContents();
            return (text);
        } catch (DukeException e) {
            throw (e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
