public class ViscountMissingDescriptionException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, the description of a %s cannot be empty.";

    public ViscountMissingDescriptionException(String taskType) {
        super(String.format(ViscountMissingDescriptionException.ERROR_MESSAGE, taskType));
    }
}
