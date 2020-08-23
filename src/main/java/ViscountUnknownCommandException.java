public class ViscountUnknownCommandException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, I do apologise but I don't know what '%s' means.";

    public ViscountUnknownCommandException(String command) {
        super(String.format(ViscountUnknownCommandException.ERROR_MESSAGE, command));
    }
}
