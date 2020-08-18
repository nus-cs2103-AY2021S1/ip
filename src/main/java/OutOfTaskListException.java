public class OutOfTaskListException extends DukeException {
    private static String outOfBoundsMsg = "Oh dear, it seems like that item does not exist in your list!";
    public OutOfTaskListException() {
        super(outOfBoundsMsg);
    }
}
