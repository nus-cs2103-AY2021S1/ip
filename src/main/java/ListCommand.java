/**
 * Handles display of Task list.
 */

public class ListCommand extends Command {
    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the List Command, displaying a list of all Tasks the user has.
     * @param tasks TaskList to be referenced.
     * @param ui For user interaction.
     * @param storage Unused.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int i = 1;
        for (Task task : tasks.getTasks()) {
            System.out.println(">> " + i++ + ". " + task);
        }
    }
}

