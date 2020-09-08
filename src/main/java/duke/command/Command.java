package duke.command;

import duke.Task;
import duke.TaskList;

/**
 * Command is an abstract class to be inherited by the various
 * types of commands that Duke is able to execute. This class
 * has one abstract method and one implemented method.
 */
public abstract class Command {
    protected String response;

    public Command() {
        response = "";
    }

    /**
     * Returns the response of the command executed.
     *
     * @return response after command executed.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Executes command input by user.
     *
     * @param t task class.
     * @param userTasks list of user tasks.
     */
    public abstract void execute (Task t, TaskList userTasks);
}
