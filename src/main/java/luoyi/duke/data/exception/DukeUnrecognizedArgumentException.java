package luoyi.duke.data.exception;

/**
 * A Duke specific exception, thrown when argument cannot be recognized by Duke.
 */
public class DukeUnrecognizedArgumentException extends IllegalArgumentException {
    public DukeUnrecognizedArgumentException(String s) {
        super(s);
    }
}
