package duke.exception;

import duke.task.TaskType;

public class EmptyTaskException extends EmptyInputException {
    public EmptyTaskException(String field, TaskType type) {
        super(String.format("The %s of %s %s cannot be empty.",
                field,
                type.compareTo(TaskType.EVENT) == 0 ? "an" : "a",
                type.toString().toLowerCase()));
    }
}
