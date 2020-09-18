package clippy.command;

/**
 * Represents a command that adds a task to a task list, based on the parsed input.
 */
public abstract class AddCommand extends Command {
    protected String taskDescription;

    /**
     * Base constructor for a command object that adds tasks.
     * @param taskDescription literal description of the task
     */
    protected AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

}
