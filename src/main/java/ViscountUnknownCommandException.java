public class ViscountUnknownCommandException extends ViscountException {
    private String command;
    private static final String ERROR_MESSAGE = "Alas, I do apologise but I don't know\nwhat '%s' means.";

    public ViscountUnknownCommandException(String command) {
        super();
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format(ViscountUnknownCommandException.ERROR_MESSAGE, this.command);
    }
}
