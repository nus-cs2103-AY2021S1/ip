package exceptions;
/**
 * DukeException class is the super class of the EmptyDeadlineException,
 * EmptyEventsException, EmptyTodoException and UnknownCommandException classses.
 * It handles all the errors face by Duke bot.
 * @author Maguire Ong
 */

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
