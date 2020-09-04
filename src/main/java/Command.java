import java.io.IOException;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    protected String str;
    protected boolean isExit = false;

    /**
     * Creates a command.
     * @param str user input
     */
    public Command(String str) {
        isExit = false;
        this.str = str;
    }

    // different command class will have different execute implementation
    public abstract String execute(TaskList list, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return this.isExit;
    }
}
