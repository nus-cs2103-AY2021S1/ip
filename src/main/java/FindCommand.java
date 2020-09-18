import java.util.ArrayList;

public class FindCommand implements Command {

    protected final String DESCRIPTION;

    public FindCommand(String description) {
        this.DESCRIPTION = description.trim();
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : taskList.TASKS) {
            if (t.description.matches("(.*)" + DESCRIPTION + "(.*)")) {
                tasks.add(t);
            }
        }
        String result = ui.showLine() + "\n" + ui.findResultMessage(tasks, DESCRIPTION) + ui.showLine();
        return result;
    }

    public boolean isExit() {
        return false;
    }
}
