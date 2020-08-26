package duke.command;

/**
 * Represents a command to add tasks.
 */
public abstract class AddCommand extends Command {

    /**
     * A description of the task to be added.
     */
    protected final String description;

    /**
     * Constructs a command that adds a task.
     *
     * @param description The description of the task.
     */
    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}