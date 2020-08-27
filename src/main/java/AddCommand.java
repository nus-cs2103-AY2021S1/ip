import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);

        if (task instanceof ToDo) {
            storage.appendToFile("T | 0 | " + task.description + "\n");
        } else if (task instanceof Deadline) {
            storage.appendToFile("D | 0 | " + task.description + " | " +
                    ((Deadline) task).by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n");
        } else if (task instanceof Event) {
            storage.appendToFile("E | 0 | " + task.description + " | " + ((Event) task).at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n");
        }

        ui.showAdded(tasks.getTask(tasks.getNumTasks()), tasks.getNumTasks());
    }

    public boolean isExit() {
        return false;
    }
}
