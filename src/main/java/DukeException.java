public class DukeException extends ArrayIndexOutOfBoundsException {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Wrong command given/lack of information";
    }
}
