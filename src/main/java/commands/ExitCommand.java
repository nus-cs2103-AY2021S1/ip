package commands;

import duke.Storage;
import duke.Ui;
import exceptions.InvalidFileException;
import tasks.TaskList;

/**
 * Represents an instruction from the user to quit the bot
 */
public class ExitCommand extends Command {

    /**
     * Prints the exit message and writes the current TaskList to the hard drive
     * @param tasks The current TaskList
     * @param ui The Ui object in use
     * @param storage The Storage object in use
     * @throws InvalidFileException If file to be written to cannot be found
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws InvalidFileException {
        ui.printExitMessage();
        storage.writeToFile("data.txt", tasks.writeString());
    }
}
