package duke.storage;

import duke.task.Task;

/**
 * Represents a list of Tasks found from finding by a keyword.
 */
public class ResultsList extends TaskList {

    /**
     * Constructor for a ResultsList.
     */
    ResultsList() {
        super();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "No results found.";
        } else {
            String result = "Your search results:\n";
            for (Task t : tasks) {
                result = result.concat(t.toString()).concat("\n");
            }
            return result;
        }
    }
}
