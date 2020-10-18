import java.util.ArrayList;

/**
 * Encapsulates a DoneCommand object, contains information about the indexes of tasks to be marked as done.
 */
public class DoneCommand extends Command {

    /**
     * Represents the indexes of tasks to be marked as done.
     */
    private final ArrayList<Integer> taskIndexes;

    DoneCommand(ArrayList<Integer> taskIndexes) {
        this.taskIndexes = taskIndexes;
    }

    /**
     * Marks tasks from the task list as done and updates the storage. 
     * 
     * @param taskList task list containing the tasks to be marked as done.
     * @param ui ui used to show the tasks that are marked as done.
     * @param storage storage used to store the taskList.
     * @return a string representation of the tasks that are marked as done.
     * @throws DukeException if updateTasks fails.
     */
    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = taskList.doneTasks(taskIndexes);
        storage.updateTasks(taskList);
        return ui.showDoneTasks(tasks);
    }
}
