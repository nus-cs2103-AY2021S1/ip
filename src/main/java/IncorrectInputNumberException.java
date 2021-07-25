/**
 * Represents an exception where there is an incorrect number of arguments in an input.
 */
public class IncorrectInputNumberException extends DukeException {
    public IncorrectInputNumberException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! You have keyed in the wrong number of inputs :(";
    }
}
