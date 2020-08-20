public class ViscountMissingArgumentException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, you need to specify the %s argument.";

    private String missingArgument;

    public ViscountMissingArgumentException(String missingArgument) {
        super();
        this.missingArgument = missingArgument;
    }

    @Override
    public String toString() {
        return String.format(ViscountMissingArgumentException.ERROR_MESSAGE, missingArgument);
    }
}
