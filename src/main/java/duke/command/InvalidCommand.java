package duke.command;

import java.util.Objects;

/**
 * Prints a notice to System.out
 */
public class InvalidCommand implements Command {

    private final String message;

    public InvalidCommand() {
        this.message = "Unrecognised Command!";
    }

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
