/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    CommandType addType;
    String description;
    String datetime;

    /**
     * Initializes the add command.
     * @param addType The type of task to add.
     * @param description The description of the task.
     * @param datetime The datetime for the task if any.
     */
    public AddCommand(CommandType addType, String description, String datetime) {
        this.addType = addType;
        this.description = description;
        this.datetime = datetime;
    }

    /**
     * Adds the task to the task list provided and shows the output to the user.
     * Saves the new task list to the disk as well.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        if (addType == CommandType.TODO) {
            if (description.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            task = new Todo(description);
        } else if (addType == CommandType.DEADLINE) {
            if (description.equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (datetime.equals("")) {
                throw new DukeException("The date of a deadline cannot be empty.");
            } else {
                task = new Deadline(description, datetime);
            }
        } else if (addType == CommandType.EVENT) {
            if (description.equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else if (datetime.equals("")) {
                throw new DukeException("The date of an event cannot be empty.");
            } else {
                task = new Event(description, datetime);
            }
        } else {
            throw new DukeException("Unknown add command.");
        }
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTaskList(tasks);
    }
}
