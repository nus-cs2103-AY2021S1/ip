package duke.exception;

/**
 * Customised Exception thrown, specific to the UI and logic errors relating to Duke's workings
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}

// nb: might have to create children exception classes in the future, avoided
//     because it's simple message passing for now
