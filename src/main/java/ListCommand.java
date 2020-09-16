import java.util.List;

public class ListCommand extends Command {
    TaskList taskManager;

    public ListCommand(TaskList taskManager) {
        super("Here are the task(s) in your list:\n");
        this.taskManager = taskManager;
    }

    public String getOutput() {
        List<Task> tasks = taskManager.getTasks();
        int size = taskManager.getTaskNum();
        String parsedTasks = "";

        int index = 1;
        for (int i = 0; i < size; i++) {
            parsedTasks = parsedTasks + "  " + index + "." + tasks.get(i) + "\n";
            index++;
        }

        this.appendDukeMessage(parsedTasks);
        return this.toString();
    }

}
