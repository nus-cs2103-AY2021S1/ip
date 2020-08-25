/**
 * Represents an executable command.
 */
public class Command {
    protected String arguments;
    protected boolean isExit;

    protected final static String ADD_MESSAGE = "Got it. I've added this task:\n";
    protected final static String DELETE_MESSAGE = "Noted. I've removed this task: \n";

    /**
     * Constructor for class Command.
     * @param arguments description of the command given by user
     */
    public Command(String arguments) {
        this.arguments = arguments;
        this.isExit = false;
    }

    /**
     * Checks if this is an exit command.
     * @return true if an exit command is executed
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command and returns the result.
     * @param tasks list of tasks given by user
     * @param storage used to load and save tasks given by user
     * @throws DukeException method should only be called for child classes
     */
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child classes");
    }
}
