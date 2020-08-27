package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.task.TaskList;
import duke.component.Ui;

public abstract class Command {

    /**
     * abstract method to be implemented by child classes.
     * @param tasks list of tasks.
     * @param ui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     * @throws DukeException exception caught when method implemented by child classes.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * method that returns false by default.
     * @return boolean to remain in loop in main method in Duke.
     */
    public boolean isExit() {
        return false;
    }

}
