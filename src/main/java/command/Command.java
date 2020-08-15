package command;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an executable command
 */
public abstract class Command {
    protected String userInput;

    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Check if the command is an ExitCommand.
     *
     * @return a boolean suggesting if the command is ExitCommand.
     */
    public boolean isExit() {
        return this.userInput.equals("bye");
    }

    /**
     * Sending a request to the command agent.
     *
     * @return a String representing the request content.
     */
    public abstract String sendRequest();

    /**
     * Return the content of the command.
     * Content differ for each type of command.
     * No content for the general Command.
     *
     * @return a list of String containing relevant
     * content from the command.
     */
    public List<String> getContent() {
        return new ArrayList<>();
    }
}
