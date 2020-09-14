package juke.command;

import juke.Storage;
import juke.TaskList;

/**
 * Represents a command to be interpreted and executed by the Juke chatbot.
 */
public abstract class Command {

    /**
     * Executes whats required based on the type of command.
     * @param taskList List of tasks
     * @param storage Storage of tasks onto disk
     * @return Response Text to be output by chatbot.
     */
    public abstract String executeCommand(TaskList taskList, Storage storage);

}
