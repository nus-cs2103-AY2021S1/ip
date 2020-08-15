public class ViscountMissingArgumentException extends ViscountException {
    private String missingArgument;
    private static final String ERROR_MESSAGE = "Alas, you need to specify the %s argument\nfor this task.";

    public ViscountMissingArgumentException(String missingArgument) {
        super();
        this.missingArgument = missingArgument;
    }

    @Override
    public String toString() {
        return String.format(ViscountMissingArgumentException.ERROR_MESSAGE, this.missingArgument);
    }
}
