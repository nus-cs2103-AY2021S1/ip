public class DukeIllegalCommandException extends DukeException {
    DukeIllegalCommandException(String command) {
        super("I'm terribly sorry to inform you that " + command + " is not an unrecognizable command.");
    }
}
