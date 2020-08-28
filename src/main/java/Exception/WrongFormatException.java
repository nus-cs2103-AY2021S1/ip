package Exception;

/**
 * Represents the exception thrown if they use the wrong format in the time and date od deadline and event.
 */
public class WrongFormatException extends DukeException {
    @Override
    public String toString() {
        String s = "OOPS!!! Please use the dd/MM/yyyy HHmm format.\n";
        return s;
    }
}
