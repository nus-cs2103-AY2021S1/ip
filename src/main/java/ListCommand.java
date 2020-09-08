import java.util.Map;

/**
 * Command to list the current tasks the user has.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by printing the list of tasks.
     * @param tasks the current list of tasks.
     * @param ui Ui object to handle printing.
    k * @param storage Storage object.
     */
    @Override
    String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage, Map<String, Runnable> runnables) {
        return String.format("Here are the tasks in your list!\n"
                + "%s", tasks);
    }

    /**
     * Returns whether the command is a command to exit.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
