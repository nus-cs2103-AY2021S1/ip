package raythx98.grandma.command;

import raythx98.grandma.exception.DukeException;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

public abstract class AddCommand extends Command {
    protected final String taskDescriptionDeadline;
    public AddCommand(String taskDescriptionDeadline) {
        this.taskDescriptionDeadline = taskDescriptionDeadline;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
