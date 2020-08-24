public class Command {
    protected String arguments;
    protected boolean isExit;

    protected final static String ADD_MESSAGE = "Got it. I've added this task:\n";
    protected final static String DELETE_MESSAGE = "Noted. I've removed this task: \n";

    Command(String arguments) {
        this.arguments = arguments;
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public CommandResult execute(TaskList tasks, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child classes");
    }
}
