public class ViscountMissingArgumentDescriptionException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, the description of the %s argument cannot be empty.";

    private String argument;

    public ViscountMissingArgumentDescriptionException(String argument) {
        super();
        this.argument = argument;
    }

    @Override
    public String toString() {
        return String.format(ViscountMissingArgumentDescriptionException.ERROR_MESSAGE, argument);
    }
}
