package duke.command;

import duke.storage.Storage;

/**
 * Represents an request by the user to tell the program to terminate.
 */
public class ExitCommand extends Command {

    /**
     *
     * @param storage The Storage object to perform the operations on.
     */
    @Override
    public void execute(Storage storage) {
        // Do nothing.
    }

    /**
     * Returns true to tell the program to terminate.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
