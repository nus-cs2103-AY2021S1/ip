package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a request from the user to find tasks containing a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor for a FindCommand.
     *
     * @param input the text to search for
     */
    public FindCommand(String input) {
        keyword = input.toLowerCase();
    }

    @Override
    public boolean shouldExit() {
        return super.shouldExit();
    }

    /**
     * Attempts to find all Tasks containing the keyword within the Storage's list,
     * then uses Ui to print the list out.
     *
     * @param storage The Storage object to search for the keyword in.
     */
    @Override
    public String execute(Storage storage) {
        TaskList list = storage.find(keyword);
        return list.toString();

    }
}
