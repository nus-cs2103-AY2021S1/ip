abstract class DukeException extends Exception {
    protected DukeException(String errorMessage) {
        super(errorMessage);
    }
}

class InvalidCommandException extends DukeException {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}

abstract class InvalidTaskException extends DukeException {
    public InvalidTaskException(String errorMessage) {
        super(errorMessage);
    }
}

class InvalidDeadlineException extends InvalidTaskException {
    public InvalidDeadlineException() {
        super("Please input the details and deadline for the task.");
    }
}

class InvalidEventException extends InvalidTaskException {
    public InvalidEventException() {
        super("Please input the details and event time for the task.");
    }
}

class InvalidTodoException extends InvalidTaskException {
    public InvalidTodoException() {
        super("Please input the details for the task.");
    }
}
