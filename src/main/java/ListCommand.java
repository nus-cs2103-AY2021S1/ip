import java.util.List;

public class ListCommand extends Command {
    public ListCommand(String action) {
        super(action);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getCount(); i++) {
            str.append(String.valueOf(i+1) + "." + tasks.getTask(i).toString() + "\n");
        }
        ui.setMessage(str.toString());
    }
}
