public class NullIndexException extends DukeException {

    public NullIndexException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Please provide an integer index for " + command + "!";
    }

}
