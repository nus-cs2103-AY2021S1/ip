/**
 * DukeException class handles exception unique to Duke
 */
public class DukeException extends RuntimeException {
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n" + super.toString() + "\n____________________________________________________________";
    }
}

