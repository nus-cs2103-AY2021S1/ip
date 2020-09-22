import java.io.IOException;
import java.util.List;

public class FindCommand extends Command {
    public FindCommand(String type, String command) {
        super(type, command);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasks();
        String message = "";
        message = message + "Here are the matching tasks in your list:\n";
        int j = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.toString().contains(command)) {
                message = message + j + "." + task.toString() + "\n";
                j++;
            }
        }

        return message;
    }
}
