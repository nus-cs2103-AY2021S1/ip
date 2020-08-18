public class InaptFollowUpCommandException extends DukeException {
    private static String outOfBoundsMsg = "My apologies, please enter an appropriate '/' command!";
    public InaptFollowUpCommandException() {
        super(outOfBoundsMsg);
    }
}
