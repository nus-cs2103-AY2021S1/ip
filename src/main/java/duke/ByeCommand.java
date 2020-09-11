package duke;

/**
 * Handles closing of application.
 */

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    /**
     * Executes the Bye duke.Command, ending the program.
     * @param tasks duke.TaskList to be added to.
     * @param ui For user interaction.
     * @param storage To store the added task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.exit(0);
        return ui.bye();
    }
}
