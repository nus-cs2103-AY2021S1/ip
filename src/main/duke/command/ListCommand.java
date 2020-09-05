package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents an request by the user to print out the list of Tasks.
 */
public class ListCommand extends Command {

    /**
     * Prints out the entire list in the storage
     *
     * @param storage The duke.storage.Storage object from which to print the list
     */
    @Override
    public String execute(Storage storage) {
        return storage.toString();
    }

}
