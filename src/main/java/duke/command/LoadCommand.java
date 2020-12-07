package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class LoadCommand implements Command {
    private String filePath;

    /**
     * Returns the command to load.
     * @param filePath to load the archived data.
     * @throws DukeException when there is error executing the command.
     */
    public LoadCommand(String filePath) throws DukeException {
        if (filePath.equals("")) {
            throw new DukeException("Invalid Input. 'load' should be followed by the filePath");
        }
        this.filePath = filePath;
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
        String message = "You have successful load the data from"
                + filePath
                + "\nTo overwrite current data, either enter 'saved' or 'bye'";
        tasks.loadArchivedTasks(storage.loadCustomFile(filePath));
        return Ui.show(message);
    }
}
