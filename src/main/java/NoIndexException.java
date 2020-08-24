public class NoIndexException extends DukeException {
    public NoIndexException(String deleteOrDone) {
        super("Invalid format. After \"" + deleteOrDone + "\", you need to put a positive integer");
    }
}