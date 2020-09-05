import java.util.Date;

/**
 * AddCommand is a request to add a Task.
 */

public class AddCommand extends Command {

    private final TaskType taskType;
    private final String description;
    private final Date date;

    /**
     * Creates a new AddCommand.
     *
     * @param taskType    Enum with restricted types.
     * @param description Description of Task to be added.
     * @param date        Date of Task if applicable.
     */
    public AddCommand(TaskType taskType, String description, Date date) {
        this.taskType = taskType;
        this.description = description;
        this.date = date;
    }

    /**
     * Adds a task to the TaskList, updates storage with added task.
     *
     * @param tasks   TaskList to be modified.
     * @param storage Storage to be updated.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = null;
        if (taskType == TaskType.TODO) {
            task = new ToDo(description, false);
        } else if (taskType == TaskType.DEADLINE) {
            task = new Deadline(description, date, false);
        } else if (taskType == TaskType.EVENT) {
            task = new Event(description, date, false);
        }
        tasks.addTask(task);
        storage.saveList(tasks);
        return Ui.getAddTask(task, tasks);
    }
}
