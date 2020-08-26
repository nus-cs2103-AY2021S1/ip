/**
 * Inherits from the DukeException class and is thrown
 * when the command "todo", "event", "deadline" or "find"
 * is used without indicating the keyword, task details or
 * task date.
 */
public class IncompleteInputException extends DukeException {

    public IncompleteInputException() {
        super("Missing description and/or date.");
    }
}
