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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvalidCommand)) return false;
        InvalidCommand that = (InvalidCommand) o;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
