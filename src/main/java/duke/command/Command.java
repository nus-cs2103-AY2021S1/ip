package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the program should exit.
     * Otherwise, the program should continue and returns false.
     *
     * @return True or False.
     */
    public boolean isExit() {
        return false;
    }
}
