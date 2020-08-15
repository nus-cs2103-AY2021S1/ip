package duke.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an executable command
 */
public abstract class Command {
    protected String content;

    public Command() {
        this.content = "";
    }

    /**
     * Check if the command is an ExitCommand via its content.
     *
     * @return a boolean suggesting if the command is ExitCommand.
     */
    public boolean isExit() {
        return this.content.equals("bye");
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
     * @return a list of String containing relevant content from the command.
     */
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add(this.content);
        return contentList;
    }
}
