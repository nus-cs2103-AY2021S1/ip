import java.util.List;

public class ShowListCommand extends Command {

    public ShowListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        taskList.updateList(storage.load());
        List<Task> tasks = taskList.storage;

        if (tasks.isEmpty()) {
            ui.print("     Tasks list is Empty.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                task.printTask(i + 1);
            }
        }
    }
}
