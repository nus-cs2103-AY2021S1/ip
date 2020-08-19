public class InvalidTaskIndexException extends DukeException {

    InvalidTaskIndexException(String command) {
        super("☹ OOPS!!! Please enter a valid task index to be " + (command.startsWith("todo")
                ? "marked as done."
                : "deleted."));
    }
}
