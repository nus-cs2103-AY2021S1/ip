package duke.exceptions;

/**
 * Error Type of a DateTimeError in Duke Application.
 * This error is thrown if the DateTime does not match or is incompatible with the DateTimeFormatter
 */
public class DukeDateTimeException extends DukeException {
    /**
     * Constructor class for DukeDateTimeException
     * @param cmd the invalid command
     */
    public DukeDateTimeException(String cmd) {
        super(cmd, 3);
    }

    /**
     * Takes in the given bad input and the code
     * @return String
     */
    public String message(String s) {
        StringBuilder b = new StringBuilder();
        b.append("Oops you did not mark your datetime! Not sure what you mean by:\n");
        b.append(badCommand).append("\n");
        b.append(s);
        b.append(": ").append(code.toString()).append("\n");
        b.append("Heres a tip, use the 'help' command to learn about my commands!\n");
        return b.toString();
    }
}
