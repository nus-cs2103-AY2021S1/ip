import java.util.ArrayList;

/**
 * Encapsulates a DeleteCommand object, contains information about the indexes of tasks to be deleted
 * from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * Represents the indexes of tasks to be deleted from the task list.
     */
    private final ArrayList<Integer> taskIndexes;

    DeleteCommand(ArrayList<Integer> taskIndexes) {
        this.taskIndexes = taskIndexes;
    }

    /**
     * Deletes tasks from the task list and updates the storage. 
     * 
     * @param taskList task list from which the tasks are deleted.
     * @param ui ui used to show the deleted tasks.
     * @param storage storage used to store the taskList.
     * @return a string representation of the deleted tasks.
     * @throws DukeException if updateTasks fails.
     */
    @Override
    String  execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = taskList.deleteTasks(taskIndexes);
        storage.updateTasks(taskList);
        return ui.showDeleteTasks(tasks, taskList.getListLength());
    }
}
