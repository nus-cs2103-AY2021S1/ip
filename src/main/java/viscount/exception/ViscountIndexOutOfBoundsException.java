package viscount.exception;

/**
 * Represents the exception when the index given is invalid.
 */
public class ViscountIndexOutOfBoundsException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, your task list does not contain a task number %d.";

    /**
     * Instantiates a new ViscountIndexOutOfBoundsException.
     *
     * @param index Index that caused the error.
     */
    public ViscountIndexOutOfBoundsException(int index) {
        super(String.format(ViscountIndexOutOfBoundsException.ERROR_MESSAGE, index + 1));
    }
}
