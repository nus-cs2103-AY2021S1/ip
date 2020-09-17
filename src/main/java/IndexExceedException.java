/**
 * A kind of Duke exception that specify given index exceed the list.
 */
public class IndexExceedException extends DukeException {
    IndexExceedException() {
        super("Sorry, the given index exceeds the number of things in your list :(");
    }
}
