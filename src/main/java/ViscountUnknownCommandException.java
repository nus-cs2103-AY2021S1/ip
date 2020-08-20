public class ViscountUnknownCommandException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, I do apologise but I don't know what '%s' means.";

    private String command;

    public ViscountUnknownCommandException(String command) {
        super();
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format(ViscountUnknownCommandException.ERROR_MESSAGE, command);
    }
}
