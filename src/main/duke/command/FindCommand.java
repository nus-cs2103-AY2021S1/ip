package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;

/**
 * Represents a request from the user to find tasks containing a keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructor for a FindCommand.
     *
     * @param input the text to search for
     */
    public FindCommand(String input) {
        assert !input.isBlank() : "Input is empty.";
        keyword = input.toLowerCase();
    }

    /**
     * Attempts to find all Tasks containing the keyword within the Storage's list,
     * then returns it as a string.
     * @param storage The Storage object to search for the keyword in.
     */
    @Override
    public String execute(Storage storage) {
        TaskList tasks = storage.find(keyword);
        if (tasks.isEmpty()) {
            return "No results found.";
        } else {
            String result = "Your search results:\n";
            for (Task t : tasks.list()) {
                result = result.concat(String.format("%d. %s\n", storage.indexOf(t) + 1, t.toString()));
            }
            return result;
        }
    }
}
