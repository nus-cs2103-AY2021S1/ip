import java.util.ArrayList;
import java.util.Date;

/**
 * AddCommand is a request to add a Task.
 */

public class AddCommand extends Command {

    private final TaskType taskType;
    private final String description;
    private final Date date;
    private Priority priority;

    /**
     * Creates a new AddCommand.
     *
     * @param taskType    Enum with restricted types.
     * @param description Description of Task to be added.
     * @param date        Date of Task if applicable.
     */
    public AddCommand(TaskType taskType, String description, Date date, Priority priority) {
        this.taskType = taskType;
        this.description = description;
        this.date = date;
        this.priority = priority;
    }

    private Task createNewTask() {
        if (taskType == TaskType.TODO) {
            return new ToDo(description, false, priority);
        } else if (taskType == TaskType.DEADLINE) {
            return new Deadline(description, date, false, priority);
        } else if (taskType == TaskType.EVENT) {
            return new Event(description, date, false, priority);
        } else {
            return null;
        }
    }

    /**
     * Adds a task to the TaskList, updates storage with added task.
     *
     * @param tasks   TaskList to be modified.
     * @param storage Storage to be updated.
     * @throws DukeException
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Storage storage) {
        Task task = createNewTask();
        assert task == null : "Task should not be null";
        tasks.addTask(task);
        storage.saveList(tasks);
        return Ui.getAddTask(task, tasks);
    }
}
