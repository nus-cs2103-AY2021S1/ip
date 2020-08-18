//Thrown when a unrecognised command is used by user
public class InaptCommandException extends DukeException {
    private static String inaptCommandMsg = "Please enter an appropriate command!";;
    public InaptCommandException() {
        super(inaptCommandMsg);
    }
}
