package duke.error;

/**
 * Represents an error that occurs when a number that is out of bound is passed in.
 *
 * @author Roger Lim
 */
public class DeleteOutOfBounds extends Exception {
    public DeleteOutOfBounds(int i) {
        super(String.format("    List only has %d items", i));
    }
}
