package duke.exception;

/**
 * Handles a Java IOException using a DukeException.
 * Prints out the error message to the user.
 */
public class DukeIoException extends DukeException {
    public DukeIoException(String message) {
        super("IOException:\n" + message, DukeIoException.class.getName());
    }

    @Override
    public String guiString() {
        return "Ngghhh...DukeTama didn't teach me to read/write from files...Please call him to ask why...";
    }
}
