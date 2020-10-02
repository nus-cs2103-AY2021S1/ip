package shiro.command;

/**
 * represents a command to add a task
 */
public abstract class AddTaskCommand extends Command {
    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    AddTaskCommand(String fullCommand) {
        super(fullCommand);
    }
}
