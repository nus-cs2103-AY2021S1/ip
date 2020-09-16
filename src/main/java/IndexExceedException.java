public class IndexExceedException extends DukeException {
    IndexExceedException() {
        super("Sorry, the given index exceeds the number of things in your list :(");
    }
}
