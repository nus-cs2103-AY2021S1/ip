package clippy.command;

/**
 * Represents a command to update a task in the TaskList.
 */
public abstract class UpdateCommand extends Command {
    protected int indexOfTaskToUpdate;

    /**
     * Constructs a command to update a task in the TaskList.
     * 
     * @param indexOfTaskToUpdate User-specified index of task to be updated.
     */
    protected UpdateCommand(int indexOfTaskToUpdate) {
        this.indexOfTaskToUpdate = indexOfTaskToUpdate;
    }

}
