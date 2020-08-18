public class MissingFollowUpCommandException extends DukeException {
    private static String outOfBoundsMsg = "Missing '/' command.";
    public MissingFollowUpCommandException() {
        super(outOfBoundsMsg);
    }
}
