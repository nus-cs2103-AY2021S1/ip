/**
 * Represents command that adds tasks.
 */
public abstract class AddCommand extends Command {
    protected String taskDescription;

    /**
     * Constructs command object that adds tasks
     * @param taskDescription literal description of the task
     */
    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Returns whether the command is an exit command by user.
     * @return true if command is exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
