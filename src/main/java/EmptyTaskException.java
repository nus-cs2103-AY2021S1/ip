public class EmptyTaskException extends EmptyInputException {
    EmptyTaskException(String message) {
        super(message);
    }

    EmptyTaskException(String field, TaskType type) {
        super(String.format("The %s of %s %s cannot be empty.",
                field,
                type.compareTo(TaskType.EVENT) == 0 ? "an" : "a",
                type.toString().toLowerCase()));
    }
}
