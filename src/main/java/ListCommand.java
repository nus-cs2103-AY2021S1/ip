import java.util.List;

public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui) {
        ui.print("Here are the tasks in your list:");
        List<Task> store = taskList.getList();
        int count = 0;
        for (Task task : store) {
            count++;
            ui.print(String.format("%d. %s", count, task));
        }
    }
}
