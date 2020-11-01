package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UndoCommand extends Command {
    public static final String COMMAND = "undo";
    public UndoCommand() { }

    /**
     * Undo the previous action that changes the array list (done, add, delete).
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     * @return A message noticing that the undo process is successful
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "task list object cannot be null";
        assert ui != null : "ui object cannot be null";
        boolean canUndo = tasks.undo();
        return canUndo ? ui.showUndoSuccessfulMsg(tasks) : ui.showUnableToUndoMessage();
    }
}
