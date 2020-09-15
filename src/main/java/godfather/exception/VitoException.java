package godfather.exception;

/**
 * Customised Exception thrown, specific to the UI and logic errors relating to Duke's workings
 */
public class VitoException extends Exception {
    public VitoException(String msg) {
        super(msg);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
