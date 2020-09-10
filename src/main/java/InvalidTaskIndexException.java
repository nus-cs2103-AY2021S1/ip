public class InvalidTaskIndexException extends DukeException {

    InvalidTaskIndexException(String command) {
        super("☹ OOPS!!! Please enter a valid task index or task indexes to be " + (command.startsWith("done")
                ? "marked as done."
                : "deleted."));
    }
}
