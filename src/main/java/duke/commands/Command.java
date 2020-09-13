package duke.commands;

import duke.TaskManager;

/**
 * Represents an abstract command.
 */
public abstract class Command {

    protected boolean isExit;
    protected String input;
    protected boolean isError;

    /**
     * Execution instructions for the command.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    public abstract String execute(TaskManager taskManager);

    public boolean isExit() {
        return isExit;
    }

    public boolean isError() {
        return isError;
    }
}
