package duke.fxcommand;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeIOException;

/**
 * The Command that saves the current taskList data to the data file.
 */
public class SaveCommand implements Command {

    /**
     * Saves the file to the data file as specified in the storage object.
     *
     * @param storage The storage object.
     * @param tasks   The taskList.
     * @throws DukeIOException If there was an IOException when saving the data.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws DukeIOException {
        ArrayList<String> data = tasks.getData();
        storage.save(data);
        return "Saved successfully!";
    }
}