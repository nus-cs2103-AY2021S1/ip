/**
 * Class to hold the UnknownCommandException message of a UnknownCommandException.
 */
public class UnknownCommandException extends DukeException {

    protected String command;

    /**
     * Constructor.
     */
    public UnknownCommandException(String s) {
        this.command = s;
    }

}
