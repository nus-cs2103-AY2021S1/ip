public class InvalidIndexException extends DukeException {
    public InvalidIndexException(int listSize) {
        super("Invalid index.\n" +
                "You have " + listSize + " items in your list.");
    }
}
