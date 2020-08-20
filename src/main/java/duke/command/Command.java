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
     * Sending a request to the command agent.
     *
     * @return a String representing the request content.
     */
    public abstract String sendRequest();

    public boolean isExit() {
        return this.getContent().get(0).equals("bye");
    }

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
