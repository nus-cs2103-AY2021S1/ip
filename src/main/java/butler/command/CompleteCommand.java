package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to mark some tasks as completed.
 * Able to mark multiple tasks as complete.
 */
public class CompleteCommand extends Command {
    private ArrayList<Integer> indexArray;

    /**
     * Constructs a command to mark some tasks as completed.
     * Tasks to be marked as completed are specified by <code>indexArray</code>.
     * <code>indexArray</code> is a list of task indexes.
     *
     * @param indexArray List of task indexes to be marked as completed.
     */
    public CompleteCommand(ArrayList<Integer> indexArray) {
        this.indexArray = indexArray;
    }

    /**
     * Marks some tasks within the specified <code>taskList</code> as completed.
     * Updates the list of tasks saved in the hard disk and
     * alerts the user that the tasks have been marked as completed.
     *
     * @param taskList List of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores given <code>taskList</code> on hard disk.
     * @throws ButlerException if an invalid index is given in <code>indexArray</code>
     *                         or an error occurs in saving the list of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        for (int i : indexArray) {
            taskList.completeTask(i);
            storage.storeTaskList(taskList);
            ui.showTaskCompleted(i);
        }
    }

    /**
     * Returns a boolean whether this command is an <code>ExitCommand</code>.
     *
     * @return <code>false</code>
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
