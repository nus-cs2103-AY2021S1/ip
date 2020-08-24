package viscount.exception;

public class ViscountNumberFormatException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, '%s' is not a valid task number.";

    public ViscountNumberFormatException(String argument) {
        super(String.format(ViscountNumberFormatException.ERROR_MESSAGE, argument));
    }
}
