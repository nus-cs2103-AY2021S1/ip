/**
 * <p>The DukeException class defines the behavior of an exception that is produced by Duke.</p>
 */
public class DukeException {
    protected final String message;
    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
