public class EmptyTimeException extends DukeException {
    EmptyTimeException() {
        this("Please specify task description. (´∀`)");
    }
    EmptyTimeException(String errorMessage) {
        super(errorMessage);
    }
}
