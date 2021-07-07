package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command class represents commands that the user specifies.
 */
public abstract class Command {
    public final boolean isExit;
    protected final String command;

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

    /**
     * Executes the command on a list of tasks specifically for FXML.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    public abstract String executeChat(TaskList list, Ui ui, Storage storage);
}
