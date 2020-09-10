package raythx.grandma.command;

import raythx.grandma.exception.DukeException;
import raythx.grandma.storage.Storage;
import raythx.grandma.task.TaskList;
import raythx.grandma.ui.Ui;

/**
 * Represents an abstract Add Command from which other Add Commands inherit.
 */
public abstract class AddCommand extends Command {
    protected final String taskDescriptionDeadline;
    public AddCommand(String taskDescriptionDeadline) {
        this.taskDescriptionDeadline = taskDescriptionDeadline;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
