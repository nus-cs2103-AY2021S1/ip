import java.util.ArrayList;

/**
 * ListCommand is a request to display all current tasks.
 */

public class ListCommand extends Command {

    /**
     * Displays all current tasks with their TaskType, done status and description.
     *
     * @param tasks   TaskList to be printed.
     * @param storage Storage is not activated.
     * @return ArrayList containing response message from Duke.
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Storage storage) {
        return Ui.getTaskList(tasks, null, null);
    }

    public static String toInputString() {
        return "list";
    }
}
