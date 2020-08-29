package exception;

/**
 * Represents an exceptions stating the command is not defined.
 */
public class UnknownCommandException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "I'm sorry, but I don't understand what you mean.\n"
                + "Here is the list of commands:\n"
                + "bye\n"
                + "deadline\n"
                + "delete\n"
                + "done\n"
                + "event\n"
                + "find\n"
                + "list\n"
                + "todo";
    }
}
