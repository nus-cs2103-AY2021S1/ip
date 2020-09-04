import java.util.ArrayList;

public class ListCommand extends Command{

    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskList = tm.getTaskList();
        String s = "";
        if (taskList.isEmpty()) {
            s = "List is empty!";
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                s = s + (i + 1) + ". " + taskList.get(i) + "\n";
            }
        }
        ui.showDetails(s);
    }
}
