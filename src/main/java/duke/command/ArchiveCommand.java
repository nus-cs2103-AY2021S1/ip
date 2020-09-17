package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the Archive Command
 */
public class ArchiveCommand implements Command {
    private String filePath;

    public ArchiveCommand(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Execute the Archive Action.
     * @param storage Storage to save data to.
     * @param tasks The tasklist to save the data to.
     * @param terminationFunction Function to run if this is the bye command.
     * @return The success message.
     * @throws DukeException when there is error carrying out the command.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Runnable terminationFunction) throws DukeException {
        storage.saveToFile(tasks.toSaveFormat(), filePath);
        return Ui.show("You have successfully archive\ncurrent progress to the file(s) indicated");
    }
}
