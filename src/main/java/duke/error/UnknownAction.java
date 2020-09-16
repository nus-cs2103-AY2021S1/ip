package duke.error;

/**
 * Represents an error that occurs a general unknown command is passed.
 *
 * @author Roger Lim
 */
public class UnknownAction extends Exception {
    public UnknownAction() {
        super("I'm sorry, but I don't know what that means");
    }
}
