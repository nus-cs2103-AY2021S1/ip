package duke.command;

import duke.*;
import java.io.IOException;

/**
 * Represents a command to shut down chat bot.
 */
public class ByeCommand extends Command {

    /**
     * Class constructor
     * @param command String parsed by Parser object
     * @param isExit Boolean indicating if chat bot should shut down.
     */
    public ByeCommand(String command, boolean isExit) {
        super(command, true);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws IOException {
        storage.saveTasks(tasks);
        ui.printSave();
        ui.printFarewell();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
