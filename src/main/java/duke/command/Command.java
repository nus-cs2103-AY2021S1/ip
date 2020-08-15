package duke.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an executable duke.command
 */
public abstract class Command {
    protected String content;

    public Command() {
        this.content = "";
    }

    /**
     * Check if the duke.command is an ExitCommand via its content.
     *
     * @return a boolean suggesting if the duke.command is ExitCommand.
     */
    public boolean isExit() {
        return this.content.equals("bye");
    }

    /**
     * Sending a request to the duke.command agent.
     *
     * @return a String representing the request content.
     */
    public abstract String sendRequest();

    /**
     * Return the content of the duke.command.
     * Content differ for each type of duke.command.
     * No content for the general Command.
     *
     * @return a list of String containing relevant
     * content from the duke.command.
     */
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add(this.content);
        return contentList;
    }
}
