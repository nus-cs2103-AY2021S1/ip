package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the user command. */
public abstract class Command {

    /** The execute method for the child classes to implement.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /** Indicates whether the program is terminating. */
    public boolean isExit() {
        return false;
    }
}
