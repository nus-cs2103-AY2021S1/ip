public class IndexOutOfBoundsDukeException extends DukeException {

    // Constructor
    public IndexOutOfBoundsDukeException(int number, int maxSize, String thing) {
        super(String.format("Cannot find %s %s with only %s %s", thing, number, maxSize, thing));
    }
}
