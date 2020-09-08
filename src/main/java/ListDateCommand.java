import java.util.ArrayList;
import java.util.Date;

public class ListDateCommand extends ListCommand {
    private final Date on;

    /**
     * Creates a ListCommand.
     *
     * @param on      Date that tasks to be displayed occur on.
     */
    public ListDateCommand(Date on) {
        this.on = on;
    }

    /**
     * Displays all current tasks with their TaskType, done status and description.
     *
     * @param tasks   TaskList to be printed.
     * @param storage Storage is not activated.
     * @return ArrayList containing response message from Duke.
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Storage storage) {
        return Ui.getTaskList(tasks, on, null);
    }

    public static String toInputString() {
        return "tasks due on";
    }
}
