package exceptions;

/**
 * UnknownCommandException class extends DukeException class
 * and handles the UnknownCommandException.
 * @author Maguire Ong
 */
public class UnknownCommandException extends DukeException {

    public UnknownCommandException() {
        super("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
