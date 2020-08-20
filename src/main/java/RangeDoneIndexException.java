public class RangeDoneIndexException extends DukeException {

    public RangeDoneIndexException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Index out of range!";
    }
}
