/**
 * A Duke specific exception, thrown when Duke detects illegal/empty argument.
 */
public class DukeIllegalArgumentException extends IllegalArgumentException {
    public DukeIllegalArgumentException(String s) {
        super(s);
    }
}
