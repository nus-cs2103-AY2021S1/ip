import java.io.IOException;
import java.util.List;

public class DeleteCommand extends Command {
    public DeleteCommand(String command, int order) {
        super(command, order);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasks();
        String message = "";
        Task task = taskList.get(order - 1);
        taskList.remove(order - 1);
        message = message + "Noted. I've removed this task:\n" + "  "
                + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list.\n";

        try {
            storage.writeToFile(taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return message;
    }
}
