public class AddCommand extends Command {
    private final CommandType addType;
    private final String description;
    private final String datetime;

    public AddCommand(CommandType addType, String description, String datetime) {
        this.addType = addType;
        this.description = description;
        this.datetime = datetime;
    }

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
