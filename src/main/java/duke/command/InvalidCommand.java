package duke.command;

/**
 * Prints a notice to System.out
 */
public class InvalidCommand implements Command {

    private final String message;

    /**
     * Create an InvalidCommand which when executed, prints out "Unrecognised Command!"
     */
    public InvalidCommand() {
        this.message = "Unrecognised Command!";
    }

    /**
     * Create an InvalidCommand which when executed, prints the custom message
     * @param message The custom message to be printed when execute() is called
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Prints a notice to System.out
     */
    @Override
    public void execute() {
        System.out.println(this.message);
    }

}
