public class ViscountMissingDescriptionException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, the description of a %s cannot be empty.";

    private String taskType;

    public ViscountMissingDescriptionException(String taskType) {
        super();
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return String.format(ViscountMissingDescriptionException.ERROR_MESSAGE, taskType);
    }
}
