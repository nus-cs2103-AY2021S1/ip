import java.util.List;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasks();
        String message = "";

        message = message + "Here are the tasks in your list:\n";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            message = message + (i + 1) + "." + task.toString() + "\n";
        }

        return message;
    }
}
