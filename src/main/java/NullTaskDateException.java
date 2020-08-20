public class NullTaskDateException extends DukeException {

    public NullTaskDateException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Please provide time to the " + command + " item!";
    }
}
