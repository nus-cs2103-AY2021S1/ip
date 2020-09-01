package duck.exception;

/**
 * Default exception thrown by Duck program.
 */
public class DuckException extends Exception {

    public DuckException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
