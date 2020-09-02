package duke.core;

public class Result {
    private String message;
    private boolean isContinuing;

    /**
     * Returns a result with the message and
     * the boolean value of whether the process should continue.
     *
     * @param message The message of the result
     * @param isContinuing The boolean value of whether the process should continue.
     */
    public Result(String message, boolean isContinuing) {
        this.message = message;
        this.isContinuing = isContinuing;
    }

    public String getMessage() {
        return message;
    }

    public boolean isContinuing() {
        return isContinuing;
    }
}
