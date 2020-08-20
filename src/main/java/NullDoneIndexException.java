public class NullDoneIndexException extends DukeException {

    public NullDoneIndexException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Please provide an index!";
    }

}
