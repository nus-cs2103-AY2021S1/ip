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
        super(
            "Please input the correct details for the deadline task.\n" +
            "deadline *description* /by *yyyy-mm-dd* *HH:mm*"
        );
    }
}

class InvalidEventException extends InvalidTaskException {
    public InvalidEventException() {
        super(
            "Please input the correct details for the event task.\n" +
            "event *description* /at *yyyy-mm-dd* *HH:mm*"
        );
    }
}

class InvalidTodoException extends InvalidTaskException {
    public InvalidTodoException() {
        super(
            "Please input the correct details for the event task.\n" +
            "todo *description*"
        );
    }
}
