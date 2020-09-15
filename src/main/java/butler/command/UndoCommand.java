package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;
import butler.task.TaskListManager;

/**
 * Represents a command to undo a number of changes to the task list.
 */
public class UndoCommand extends Command {
    private final int undoCount;

    /**
     * Constructs a command to undo some commands.
     *
     * @param undoCount Number of commands to undo.
     */
    public UndoCommand(int undoCount) {
        this.undoCount = undoCount;
    }

    /**
     * Undo a given number of commands.
     * Updates the list of tasks saved in the hard disk and
     * alerts the user that changes have been undone.
     *
     * @param taskListManager Manager of the list of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores current list of tasks on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if <code>undoCount</code> is an invalid number.
     */
    @Override
    public String execute(TaskListManager taskListManager, Ui ui, Storage storage) throws ButlerException {
        TaskList taskList = taskListManager.getPastTaskList(undoCount);
        storage.storeTaskList(taskList);
        return ui.showOldTaskList(taskList);
    }

    /**
     * Returns true if this command is an <code>ExitCommand</code>.
     *
     * @return <code>false</code>.
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
