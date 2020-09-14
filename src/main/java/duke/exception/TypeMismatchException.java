package duke.exception;

public class TypeMismatchException extends DukeException {
    public TypeMismatchException(String command) {
        super("Error! Integer should follow '" + command + "' command.");
    }
}
