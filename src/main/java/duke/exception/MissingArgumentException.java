package duke.exception;

public class MissingArgumentException extends DukeException{

    public MissingArgumentException(String str) {
        super("Missing Argument: " + str);
    }
}
