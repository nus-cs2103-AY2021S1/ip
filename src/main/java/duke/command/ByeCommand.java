package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeIOException;

/**
 * Represents the Command to exit Duke.
 */
public class ByeCommand implements Command {

    /**
     * Saves the current taskList into harddisk and
     * prints the exit message before exiting Duke.
     *
     * @param ui      The ui of Duke.
     * @param storage The storage object.
     * @param tasks   The taskList.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeIOException {
        // COPIED FROM SAVECOMMAND
        ArrayList<String> data = tasks.getData();
        storage.save(data);
        ArrayList<String> toPrint = new ArrayList<>(List.of("Saved successfully!"));
        ui.printWithWrapper(toPrint, false, false);

        ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
