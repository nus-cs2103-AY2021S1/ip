package duke.result;

/**
 * After an <code>Operation</code> is executed, a <code>Result</code> object is created and set back to Duke.
 * It encapsulates the status of the <code>Operation</code>.
 */
public class Result {
    private final boolean isSuccessful;
    private final String message;
    private final boolean isExit;

    /**
     * Constructor method.
     *
     * @param isSuccessful indicates if the <code>Operation</code> is successful.
     * @param message indicates the message of the <code>Operation</code>.
     * @param isExit indicates if Duke should stop running.
     */
    public Result(boolean isSuccessful, String message, boolean isExit) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.isExit = isExit;
    }

    /**
     * Determines if the executed <code>Operation</code> was successful.
     *
     * @return <code>true</code> if the <code>Operation</code> was successful.
     */
    public boolean isSuccessful() {
        return isSuccessful;
    }

    /**
     * Determines if the executed <code>Operation</code> was an <code>ExitOperation</code>.
     *
     * @return <code>true</code> if it is an <code>ExitOperation</code>.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Gets the message of the executed <code>Operation</code>.
     *
     * @return the message <code>String</code>.
     */
    public String getMessage() {
        return message;
    }

}
