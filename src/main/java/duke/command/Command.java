package duke.command;

import duke.Bot;
import duke.task.TaskList;

/**
 * This interface represents an action which Duke can take in response to user input.
 */
public interface Command {

    /**
     * Executes this Command.
     *
     * @param bot the bot which the Command can interact with
     * @param list the TaskList which the Command can read and modify
     */
    public void execute(Bot bot, TaskList list);
}
