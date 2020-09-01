package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** This abstract class is a generic class which all other command classes inherit from. */
public abstract class Command {

    /**
     * Performs the necessary actions associated with the command.
     *
     * @param taskList The TaskList object to make changes to or to get tasks from.
     * @param ui The Ui that saves messages to be sent to the user.
     * @param storage The Storage object to make changes to or to get tasks from.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
