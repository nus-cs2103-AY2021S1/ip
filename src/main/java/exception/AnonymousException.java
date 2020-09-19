package exception;

/**
 * Thrown to notify user that enter a
 * non-existing command.
 */
public class AnonymousException extends DukeException {
    private String input;

    public AnonymousException(String input) {
        this.input = input;
    }

    /**
     * Returns a short description of this throwable.
     * The result is the concatenation of the name of
     * the incorrect commend and a suggestion to run
     * the help command.
     *
     * @return String exception message
     */
    @Override
    public String toString() {
        return super.toString() + " There are no commands that start with \"" + this.input + "\".\n"
                + "Please type --help to see all the commands";
    }
}
