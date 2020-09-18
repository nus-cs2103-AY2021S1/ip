package clippy.command;

/**
 * Represents a command that adds a task to a task list, based on the parsed input, when executed.
 */
public abstract class AddCommand extends Command {
    protected String taskDescription;

    /**
     * Constructs a command object that adds a task when executed.
     * 
     * @param taskDescription Literal description of the task.
     */
    protected AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

}
