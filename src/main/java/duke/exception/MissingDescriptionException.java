package duke;

public class MissingDescriptionException extends MissingElementException {
    public MissingDescriptionException(TaskType type) {
        super("The description of " + type.toString().toLowerCase() + " cannot be empty.");
    }
}
