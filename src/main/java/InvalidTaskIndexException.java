public class InvalidTaskIndexException extends DukeException {

    public InvalidTaskIndexException() {
        super("☹ OOPS!!! This task index does not exist in your list.");
    }
}
