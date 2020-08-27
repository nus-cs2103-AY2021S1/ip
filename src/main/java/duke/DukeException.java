package duke;

public class DukeException extends Exception {
    public DukeException(ExceptionTypeEnum type) {
        super(type.toString());
    }
}
