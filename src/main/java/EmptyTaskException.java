public class EmptyTaskException extends DukeException {
    EmptyTaskException() {
        this("Please specify task index. (´∀`)");
    }
    EmptyTaskException(String errorMessage) {
        super(errorMessage);
    }
}
