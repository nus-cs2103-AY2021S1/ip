package commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected String description;

    public abstract CommandResult execute(TaskList tasks, Storage storage);

}
