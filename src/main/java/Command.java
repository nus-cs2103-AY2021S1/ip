import java.io.IOException;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    protected String str;

    /**
     * Creates a command.
     * @param str user input
     */
    public Command(String str) {
        this.str = str;
    }

    // different command class will have different execute implementation
    public abstract String execute(TaskList list, Ui ui, Storage storage) throws IOException;
}
