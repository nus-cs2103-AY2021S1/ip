import java.util.List;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        if (tasks.size() == 0) {
            ui.printResponse("No task added yet...");
            return;
        }
        StringBuilder taskMessage = new StringBuilder();
        taskMessage.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String task = String.format("\n\t%d.%s", (i + 1), tasks.get(i));
            taskMessage.append(task);
        }
        ui.printResponse(taskMessage.toString());
    }
}
