public class ViscountMissingDescriptionException extends ViscountException {
    private String taskType;
    private static final String ERROR_MESSAGE = "Alas, the description of a %s\ncannot be empty.";

    public ViscountMissingDescriptionException(String taskType) {
        super();
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return String.format(ViscountMissingDescriptionException.ERROR_MESSAGE, this.taskType);
    }
}
