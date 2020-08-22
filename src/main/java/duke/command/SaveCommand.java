package duke.command;

import duke.TaskList;
import duke.exception.DukeIOException;
import duke.Storage;
import duke.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * The Command that saves the current
 * taskList data to the data file.
 */
public class SaveCommand implements Command {
    /**
     * Saves the file to the data file as
     * specified in the storage object.
     * @param ui The ui of Duke.
     * @param storage The storage object.
     * @param tasks The taskList.
     * @throws DukeIOException If there was an IOException
     * when saving the data.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeIOException {
        ArrayList<String> data = tasks.getData();
        storage.save(data);
        ArrayList<String> toPrint = new ArrayList<>(List.of("Saved successfully!"));
        ui.printWithWrapper(toPrint, false, false);
    }
}