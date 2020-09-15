package duke.error;

/**
 * Represents an error that occurs when the incorrect format is used.
 *
 * @author Roger Lim
 */
public class IncorrectFormat extends Exception {
    public IncorrectFormat() {
        super("    Please use the correct format. Thank you");
    }
}
