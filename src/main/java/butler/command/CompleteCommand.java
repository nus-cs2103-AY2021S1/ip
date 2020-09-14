package butler.command;

import java.util.ArrayList;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

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
     * Marks multiple tasks within the specified <code>taskList</code> as completed.
     * Updates the list of tasks saved in the hard disk and
     * alerts the user that the tasks have been marked as completed.
     *
     * @param taskList List of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores given <code>taskList</code> on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if an invalid index is given in <code>taskIndexList</code>
     *                         or an error occurs in saving the list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        StringBuilder output = new StringBuilder();
        for (int index : taskIndexList) {
            taskList.completeTask(index);
            storage.storeTaskList(taskList);
            output.append(ui.showTaskIsCompleted(index));
        }
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
