/**
 * Provides contract for Command subclasses.
 */

public abstract class Command {
    /**
     * Constructor for Command.
     */
    public Command() {
    }

    /**
     * Contract for Command execution.
     * @param tasks TaskList to be added to.
     * @param ui For user interaction.
     * @param storage To store the added task.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
