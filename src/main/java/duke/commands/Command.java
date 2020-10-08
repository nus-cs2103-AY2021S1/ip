package duke.commands;

import duke.tasklist.TaskList;

/**
 * Represents a user command. Each individual command should extend
 * from this abstract class and implement the execute method.
 */
public abstract class Command {

    /**
     * Executes the command. This method will be implemented by the child classes.
     *
     * @param taskList The taskList involved.
     * @return The result of the command.
     */
    public abstract CommandResult execute(TaskList taskList);

}
