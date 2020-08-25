package exception;

public class CorruptedDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public CorruptedDataException() {
        super("CorruptedDataException");
    }
}