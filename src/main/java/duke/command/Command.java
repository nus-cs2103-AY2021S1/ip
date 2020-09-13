package duke.command;

import java.util.ArrayList;
import java.util.List;

/** Represents an executable command */
public abstract class Command {
    protected String content;

    public Command() {
        this.content = "";
    }

    /**
     * Sends a request to the command agent.
     *
     * @return A String representing the request content.
     */
    public abstract String sendRequest();

    /**
     * Checks if the command is an ExitCommand by reviewing its content.
     *
     * @return A boolean value, true if it is an ExitCommand, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the content of the command; content differ for each type of command.
     * No content for the general Command.
     *
     * @return A list of String containing relevant content from the command.
     */
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add(this.content);
        return contentList;
    }
}
