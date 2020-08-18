public class MissingFollowUpCommandException extends DukeException {
    private static String outOfBoundsMsg = "Oh dear, there seems to be a missing '/' command.";
    public MissingFollowUpCommandException() {
        super(outOfBoundsMsg);
    }
}
