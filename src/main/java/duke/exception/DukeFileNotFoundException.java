package duke.exception;

/**
 * Handles a Java FileNotFoundException using a DukeException.
 * Prints out the error message to the user.
 */
public class DukeFileNotFoundException extends DukeException {
    public DukeFileNotFoundException(String message) {
        super("File cannot be found! See the following:\n" + message, DukeFileNotFoundException.class.getName());
    }

    @Override
    public String guiString() {
        return "Ngghhh...DukeTama didn't pass me your data file...Please call him to ask why...";
    }
}
