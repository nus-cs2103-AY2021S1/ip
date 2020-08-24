import java.io.IOException;

public class AddCommand extends Command {
    private final Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        String output = "Got it. I've added this task:\n";
        output += ("\t " + task.toString() + "\n");
        output += ("\t Now you have " + tasks.getSize() + (tasks.getSize() > 1 ? " tasks" : " task") + " in the list.");
        ui.displayMessage(output);
        try {
            storage.writeData(tasks.getTasks());
        } catch (IOException e) {
            throw new StorageAccessException("☹ OOPS!!! Something went wrong when saving the new task.");
        }
    }
}
