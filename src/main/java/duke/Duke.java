package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Dook the all purpose chatbot to serve your human needs. Made from the finest IDEs, Dook will be the most
 * efficient task list you have ever laid your hands upon.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * To instantiate a Dook instance.
     */
    public Duke() {
        storage = new Storage("./data");
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
}
