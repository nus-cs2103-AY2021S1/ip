package duke;

/**
 * Provides contract for duke.Command subclasses.
 */

public abstract class Command {
    /**
     * Constructor for duke.Command.
     */
    public Command() {
    }

    /**
     * Contract for duke.Command execution.
     * @param tasks duke.TaskList to be added to.
     * @param ui For user interaction.
     * @param storage To store the added task.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
