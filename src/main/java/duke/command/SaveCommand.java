package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class SaveCommand implements Command {

    public SaveCommand() {
    }

    /**
     * Execute the command.
     *
     * @param storage             Storage to save data to.
     * @param tasks               The tasklist to save the data to.
     * @param terminationFunction Function to run if this is the bye command.
     * @return The response of Duke to the user Input.
     * @throws DukeException if the system fails to execute.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Runnable terminationFunction) throws DukeException {
        storage.saveToFile(tasks.toSaveFormat());
        return Ui.show("Current Progress is saved.\nYou can proceed with next command as per normal");
    }
}
