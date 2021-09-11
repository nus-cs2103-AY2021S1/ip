package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public interface Command {
    /**
     * Takes user input command and interprets that accordingly
     * @param tasks TaskList object
     * @param text user input text
     * @return message
     * @throws DobbyException
     */
    String parseInput(TaskList tasks, String text) throws DobbyException;
}
