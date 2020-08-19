public class EmptyDescriptionException extends DukeException {

    EmptyDescriptionException(String task) {
        super("☹ OOPS!!! The description of a " + task + " cannot be empty.");
    }

}
