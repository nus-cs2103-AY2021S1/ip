public class NoDescriptionException extends DukeException{
    NoDescriptionException(String task) {
        super(String.format("  ☹ OOPS!!! The description of a %s cannot be empty.", task));
    }
}
