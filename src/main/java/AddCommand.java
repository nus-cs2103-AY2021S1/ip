import java.util.Date;

/**
 * AddCommand is a request to add a Task.
 */

public class AddCommand extends Command {

	private final TaskType taskType;
	private final String description;
	private final Date date;

	public AddCommand(TaskType taskType, String description, Date date) {
		this.taskType = taskType;
		this.description = description;
		this.date = date;
	}

	/**
	 * Adds a task to the TaskList, alerts user that a task is added, updates storage with added task.
	 *
	 * @param tasks TaskList to be modified.
	 * @param ui Ui to be used to display feedback messages.
	 * @param storage Storage to be updated.
	 * @throws DukeException
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		Task task = null;
		if (taskType == TaskType.TODO) {
			task = new ToDo(description, false);
		} else if (taskType == TaskType.DEADLINE) {
			task = new Deadline(description, date, false);
		} else if (taskType == TaskType.EVENT) {
			task = new Event(description, date, false);
		}
		if (task != null) {
			tasks.addTask(task);
			ui.formatAddTask(task, tasks);
			storage.saveList(tasks);
		}
	}
}
