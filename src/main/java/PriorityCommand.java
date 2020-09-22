import java.io.IOException;
import java.util.List;

public class PriorityCommand extends Command {
    public PriorityCommand(String command, int order, int priority) {
        super(command, order, priority);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasks();

        String message = "";

        Task task = taskList.get(order - 1);
        task.setPriority(priority);
        message = message + "set the priority of task " + order + " to be " + priority;

        try {
            storage.writeToFile(taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return message;
    }
}
