//Thrown when a unrecognised command is used by user
public class InaptCommandException extends DukeException {
    private static String inaptCommandMsg = "I beg you pardon, I did not catch what you are saying.";;
    public InaptCommandException() {
        super(inaptCommandMsg);
    }
}
