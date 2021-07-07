package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents an executable command responsible for
 * adding tasks to the task list and storage.
 */
public abstract class Command {
    protected String description;

    /**
     * Executes the command, handling the command's logic
     * and returning a message to be printed contained in CommandResult.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult containing the message to the user.
     */
    public abstract CommandResult execute(TaskList tasks, Storage storage);

}
