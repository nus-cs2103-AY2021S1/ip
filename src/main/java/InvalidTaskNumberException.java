public class InvalidTaskNumberException extends DukeException {
    protected InvalidTaskNumberException(int size) {
        super(String.format("Task number does not exist in the list.\n    Your current list only has %d tasks!", size));
    }
}
