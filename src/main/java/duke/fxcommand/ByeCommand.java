package duke.fxcommand;

import java.util.ArrayList;

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
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeIOException {
        ArrayList<String> data = tasks.getData();
        storage.save(data);
        return "Saved successfully!\nBye bye! Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
