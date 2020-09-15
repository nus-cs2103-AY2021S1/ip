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
    private Ui ui;

    /**
     * To instantiate a Dook instance with a given memory file path.
     *
     * @param filePath  File path of memory file.
     */
    public Duke() {
        ui = new Ui();
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
