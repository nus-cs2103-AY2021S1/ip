package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TagList;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListTagCommand extends Command {

    /**
     * Returns true if and only if it is a command to exit the program.
     *
     * @return true or false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and performs operations accordingly.
     *
     * @param taskList taskList that stores Task objects
     * @param ui       Ui that handles input and output to User
     * @param storage  storage that handles data storage
     * @throws IOException   Ioexception
     * @throws DukeException DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        return TagList.listTags();
    }
}
