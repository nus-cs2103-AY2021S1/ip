package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {
    protected final String command;
    public final boolean isExit;

    /**
     * Represents an abstract Command forming the parent to other commands.
     */
    public Command(String command, boolean isExit) {
        this.command = command;
        this.isExit = isExit;
    }

    /**
     * Executes the command on a list of tasks.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage);
}
