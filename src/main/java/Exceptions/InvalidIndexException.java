public class InvalidIndexException extends Exception {
    public InvalidIndexException() {
        super("☹ OOPS!!! The item does not exist within the list");
    }
}