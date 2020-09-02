package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class AddCommand extends Command {
    private Task taskToAdd;

    /**
     * Constructs a new add task
     * @param task given task to be added
     */
    public AddCommand(Task task) {
        super();
        this.taskToAdd = task;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addATask(this.taskToAdd);
        storage.writeData(tasks);
        StringBuilder str = new StringBuilder("Added new task:\n");
        str.append(taskToAdd.toString());
        str.append("\nYou now have " + tasks.getNumberOfTasks() + " tasks!");
        return str.toString();
    }
}
