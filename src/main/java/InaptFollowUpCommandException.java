public class InaptFollowUpCommandException extends DukeException {
    private static String outOfBoundsMsg = "Please enter an appropriate '/' command!";
    public InaptFollowUpCommandException() {
        super(outOfBoundsMsg);
    }
}
