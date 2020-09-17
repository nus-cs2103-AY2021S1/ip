package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents the task manager program.
 */
public class Duke {

    protected TaskList taskList = new TaskList();
    protected Ui ui = new Ui(taskList);

    /**
     * Gets string response from Duke.
     * Duke takes in console input as a command and will execute
     * and return the appropriate output.
     */
    String getResponse(String input) {
        String reply;
        try {
            Command c = Parser.parse(input);
            reply  = c.execute(taskList, ui, input);
        } catch (DukeException e) {
            reply = e.toString();
        }
        return "Duck: " + reply;
    }
}

