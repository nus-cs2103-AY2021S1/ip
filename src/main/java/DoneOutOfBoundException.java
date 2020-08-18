public class DoneOutOfBoundException extends DukeException {
    private static String outOfBoundsMsg = "That item does not exist in your list! The input number has exceeded your list size!";
    public DoneOutOfBoundException() {
        super(outOfBoundsMsg);
    }
}
