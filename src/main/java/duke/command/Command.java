package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public boolean isBye = false;

<<<<<<< HEAD
    /**
     * Executes action done to task in TaskList.
     *
     * @param storage Storage data of tasks in hard disk.
     * @param taskList TaskList where task actions are done.
     * @param ui Ui that shows relevant messages to user.
     */
=======
>>>>>>> branch-A-CodingStandard
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;
}
