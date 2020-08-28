package Exception;

/**
 * Represents the exception thrown if there are no such task in the list.
 */
public class DoneOutOfBoundException extends DoneException {
    @Override
    public String toString() {
        String s = "OOPS!!! There are no such task.\n";
        return s;
    }
}
