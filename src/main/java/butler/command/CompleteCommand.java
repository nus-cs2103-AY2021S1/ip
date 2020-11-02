package butler.command;

import java.util.ArrayList;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;
import butler.task.TaskListManager;

/**
 * Represents a command to mark multiple tasks as completed.
 */
public class CompleteCommand extends Command {
    private final ArrayList<Integer> taskIndexList;

    /**
     * Constructs a command to mark multiple tasks as completed.
     * Task indexes are specified by <code>taskIndexList</code>.
     *
     * @param taskIndexList List of task indexes to be marked as completed.
     */
    public CompleteCommand(ArrayList<Integer> taskIndexList) {
        this.taskIndexList = taskIndexList;
    }

    /**
     * Marks multiple tasks within the current list of tasks as completed.
     * Updates the list of tasks saved in the hard disk and
     * alerts the user that the tasks have been marked as completed.
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
            newTaskList.completeTask(index);
            storage.storeTaskList(newTaskList);
            output.append(ui.showTaskIsCompleted(index));
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
