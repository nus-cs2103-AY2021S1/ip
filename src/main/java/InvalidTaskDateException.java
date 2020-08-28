public class InvalidTaskDateException extends DukeException {

    public InvalidTaskDateException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Cannot parse the given " + command + " date!";
    }
}
