package duke.exceptions;

public class NullCommandException extends DukeException {
    /**
     * Constructor of null command exception.
     * @param err error message
     */
    public NullCommandException(String err) {
        super(err);
    }

    @Override
    public String getMessage() {
        return "🙈OOPS!!! The command cannot be empty";
    }

}
