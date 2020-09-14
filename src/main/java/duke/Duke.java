package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.commands.StartCommand;
import duke.exceptions.DukeException;

/**
 * Represents a Duke.
 */
public class Duke {
    private TaskManager taskManager;
    private boolean isExit;
    private boolean isError;

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
        String response = command.execute(taskManager);
        this.isError = command.isError();
        this.isExit = command.isExit();
        return response;
    }

    public boolean isError() {
        return isError;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns the message for when Duke welcomes the user.
     * @return Welcome message in String.
     */
    public String getWelcome() {
        Command command = new StartCommand("");
        return command.execute(taskManager);
    }
}
