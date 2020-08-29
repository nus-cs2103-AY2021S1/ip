package duke.commands;

import duke.TaskManager;

/**
 * Represents an abstract command.
 */
public abstract class Command {

    public boolean isExit;
    String input;

    /**
     * Execution instructions for the command.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    public abstract String execute(TaskManager taskManager);
}
