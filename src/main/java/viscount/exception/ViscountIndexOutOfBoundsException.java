package viscount.exception;

public class ViscountIndexOutOfBoundsException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, your task list does not contain a task number %d.";

    public ViscountIndexOutOfBoundsException(int index) {
        super(String.format(ViscountIndexOutOfBoundsException.ERROR_MESSAGE, index + 1));
    }
}
