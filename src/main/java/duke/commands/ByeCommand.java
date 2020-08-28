package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeIOException;
import duke.utils.DukeStdMsg;

/**
 * Represents the Command to exit Duke.
 */
public class ByeCommand implements Command {

    /**
     * Saves the current taskList into harddisk and returns the exit message.
     *
     * @param storage The storage object.
     * @param tasks   The taskList.
     * @return The exit message.
     * @throws DukeIOException IOException when writing data to the data-file.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws DukeIOException {
        ArrayList<String> data = tasks.getData();
        storage.save(data);
        return DukeStdMsg.EXIT.getMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
