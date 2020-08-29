package duke;

/**
 * Handles display of duke.Task list.
 */

public class ListCommand extends Command {
    /**
     * Constructor for duke.ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the List duke.Command, displaying a list of all Tasks the user has.
     * @param tasks duke.TaskList to be referenced.
     * @param ui For user interaction.
     * @param storage Unused.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();
        response.append(">> Your tasks:");
        int i = 1;
        for (Task task : tasks.getTasks()) {
            response.append("\n>> " + i++ + ". " + task);
        }
        return response.toString();
    }
}

