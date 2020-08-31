/**
 * A subclass of Command.
 * Handles "list" command.
 */
public class ListCommand extends Command {
    private static final String TAB = "  ";
    private static final String LIST_TITLE = TAB + " Here are the tasks in your list:";

    /**
     * Executes the command.
     * @param tasks a list of tasks.
     * @param ui ui.
     * @param storage a storage working on data file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int i = 1;
        String output = LIST_TITLE + "\n";
        for (Task task : tasks.getTaskList()) {
            output += TAB + " " + i++ + "." + task + "\n";
        }
        return output;
    }

    /**
     * Returns isDone to stop user from entering command.
     * @return false to continue to accept user input.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
