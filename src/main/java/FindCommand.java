import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {

    private String description;

    /**
     * Constructor for the class.
     * @param description
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Execute the command.
     * @param tasks
     * @param ui
     * @param storage
     * @return a String of message.
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks.getList()) {
            if (t.name.contains(description)) {
                foundTasks.add(t);
            }
        }

        return ui.generateList(new TaskList(foundTasks));
    }

    /**
     * Check if the command is an exit command.
     * @return false as it is not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
