package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
/**
 * Represents the Archive Command
 */
public class ByeCommand implements Command {
    /**
     * Create instance of ByeCommand
     */
    public ByeCommand() {
    }

    /**
     * Execute the Bye Command.
     * @param storage Storage to save data to.
     * @param tasks The tasklist to save the data to.
     * @param terminationFunction Function to run if this is the bye command.
     * @return The Bye Message.
     * @throws DukeException when there is error carrying out the command.
     */
    public String execute(Storage storage, TaskList tasks, Runnable terminationFunction) throws DukeException {
        storage.saveToFile(tasks.toSaveFormat());
        terminationFunction.run();
        return Ui.showBye();
    }
}
