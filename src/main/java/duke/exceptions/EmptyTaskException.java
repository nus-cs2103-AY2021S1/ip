package duke.exceptions;

public class EmptyTaskException extends DukeException {
    public EmptyTaskException() {
        this("Please specify task index. (´∀`)");
    }
    public EmptyTaskException(String errorMessage) {
        super(errorMessage);
    }
}
