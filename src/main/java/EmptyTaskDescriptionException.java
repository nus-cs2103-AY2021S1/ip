public class EmptyTaskDescriptionException extends IllegalArgumentException {

    public EmptyTaskDescriptionException(String taskType) {
        super(String.format("OOPS! The description of a %s cannot be empty.", taskType));
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
