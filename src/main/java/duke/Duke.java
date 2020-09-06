package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.DukeException;

/**
 * Represents a Duke.
 */
public class Duke {
    private TaskManager taskManager;
    private boolean isExit;

    /**
     * Initializes the logic unit behind Duke.
     */
    public Duke() {
        try {
            this.taskManager = new TaskManager();
        } catch (DukeException | IOException exception) {
            System.out.println(exception.getMessage());
            this.taskManager = null;
        }
    }

    /**
     * Returns the response after executing the command
     * associated with the user input.
     *
     * @param input User input
     * @return Response from execution of the command
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        return command.execute(taskManager);
    }

}
