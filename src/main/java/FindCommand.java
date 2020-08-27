import java.util.ArrayList;

public class FindCommand implements Command {

    protected final String command;

    public FindCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (command.trim().length() == 4) {
            throw new MissingDescriptionException();
        }
        String query = command.substring(4).trim();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : taskList.tasks) {
            if (t.description.matches("(.*)" + query + "(.*)")) {
                tasks.add(t);
            }
        }
        ui.showLine();
        ui.findResultMessage(tasks, query);
        ui.showLine();
    }

    public boolean isExit() {
        return false;
    }
}
