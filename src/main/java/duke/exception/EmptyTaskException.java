package duke.exception;

import duke.task.TaskType;

public class EmptyTaskException extends DukeException {
    public EmptyTaskException(TaskType complexTask) {
        super(String.format("The description of a %s cannot be empty.", complexTask));
    }
}
