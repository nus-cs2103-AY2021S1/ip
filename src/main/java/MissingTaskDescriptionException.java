public class MissingTaskDescriptionException extends Exception {

    public String taskType;

    public MissingTaskDescriptionException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! The description of a %s cannot be empty.", taskType);
    }
}
