package duke.exception;

public class FindIncompleteException extends InvalidInputException {
    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return " Oh no! Please specify a keyword of the task that you "
                + "\n" + " want to find.";
    }
}
