package duke.command;

import duke.Gui.Gui;
import duke.component.DukeException;
import duke.component.Storage;
import duke.task.TaskList;

import java.util.ArrayList;

public abstract class Command {

    /**
     * abstract method to be implemented by child classes.
     * @param tasks list of tasks.
     * @param gui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     * @throws DukeException exception caught when method implemented by child classes.
     */
    public abstract ArrayList<String> execute(TaskList tasks, Gui gui, Storage storage,
        ArrayList<String> responseList) throws DukeException;

    /**
     * method that returns false by default.
     * @return boolean to remain in loop in main method in Duke.
     */
    public boolean isExit() {
        return false;
    }

}
