import java.util.ArrayList;

public class FindCommand implements Command {

    protected final String DESCRIPTION;

    public FindCommand(String description) {
        this.DESCRIPTION = description;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : taskList.TASKS) {
            if (t.description.matches("(.*)" + DESCRIPTION + "(.*)")) {
                tasks.add(t);
            }
        }
        ui.showLine();
        ui.findResultMessage(tasks, DESCRIPTION);
        ui.showLine();
    }

    public boolean isExit() {
        return false;
    }
}
