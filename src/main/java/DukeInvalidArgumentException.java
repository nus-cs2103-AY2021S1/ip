public class DukeInvalidArgumentException extends DukeException {

    public DukeInvalidArgumentException(String command) {
        super("Please enter a valid argument for " + command + "!");
    }
}
