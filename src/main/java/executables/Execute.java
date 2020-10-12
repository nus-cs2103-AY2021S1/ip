package executables;

import exceptions.DukeException;
import storage.TaskList;
import ui.UI;

public abstract class Execute {



    /**
     * Abstract method that executes a command.
     *
     * @param tasks taskList containing tasks
     * @param ui to interact with user
     * @return String output to be output by parser
     * @throws DukeException if commands are not valid
     */
    abstract String execute(TaskList tasks, UI ui) throws DukeException;

}
