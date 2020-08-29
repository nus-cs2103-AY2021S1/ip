/**
 * A subclass of Command.
 * Handles "list" command.
 */
public class ListCommand extends Command {
    private final String TAB = "  ";
    private final String LIST_TITLE = TAB + " Here are the tasks in your list:";

    /**
     * Executes the command.
     * @param tasks a list of tasks.
     * @param ui ui.
     * @param storage a storage working on data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int i = 1;
        System.out.println(LIST_TITLE);
        for (Task task : tasks.getTaskList()) {
            System.out.println(TAB + " " + i++ + "." + task);
        }
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
