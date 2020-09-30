package butler.command;

import java.util.ArrayList;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;
import butler.task.TaskListManager;

/**
 * Represents a command to delete a <code>Task</code> from the current list of tasks.
 */
public class DeleteCommand extends Command {
    private final ArrayList<Integer> taskIndexList;

    /**
     * Constructs a command to delete a task with specified <code>taskIndex</code>.
     *
     * @param taskIndexList List of task indexes to be deleted.
     */
    public DeleteCommand(ArrayList<Integer> taskIndexList) {
        this.taskIndexList = taskIndexList;
    }

    /**
     * Deletes the tasks specified within <code>taskIndexList</code> from the current list of tasks.
     * Updates the list of tasks saved in the hard disk and
     * alerts the user that the task has been successfully deleted.
     *
     * @param taskListManager Manager of the list of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores current list of tasks on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if an invalid index is given in <code>taskIndexList</code>.
     */
    @Override
    public String execute(TaskListManager taskListManager, Ui ui, Storage storage) throws ButlerException {
        StringBuilder output = new StringBuilder();
        TaskList newTaskList = taskListManager.getCurrentTaskList().copy();
        for (int index : taskIndexList) {
            newTaskList.deleteTask(index);
            storage.storeTaskList(newTaskList);
            output.append(ui.showTaskIsDeleted(index));
        }
        taskListManager.addLatestTaskList(newTaskList);
        storage.storeTaskList(newTaskList);
        return output.toString();
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
