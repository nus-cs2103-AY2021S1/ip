package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Duke the all purpose chatbot to serve your human needs. Made from the finest IDEs, Duke will be the most
 * efficient task list you have ever laid your hands upon.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * To instantiate a Duke instance.
     */
    public Duke() {
        storage = new Storage("./data");
        tasks = new TaskList(storage);
    }

    /**
     * Instantiate Duke instance with custom Storage.
     * @param s  Custom Storage to be used with duke.
     */
    public Duke(Storage s) {
        storage = s;
        tasks = new TaskList(storage);
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String lineToPrint = c.execute(tasks, storage);
            return lineToPrint;
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public TaskList getTasks() {
        return tasks;
    }
}
