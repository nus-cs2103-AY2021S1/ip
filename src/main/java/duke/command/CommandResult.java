package duke.command;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final boolean isExit;

    /**
     * Class constructor.
     * @param feedbackToUser A string representing the response for the user.
     * @param isExit         <code>true</code> if the command signals exiting the chat bot;
     *                       <code>false</code> otherwise.
     */
    public CommandResult(String feedbackToUser, boolean isExit) {
        this.feedbackToUser = feedbackToUser;
        this.isExit = isExit;
    }

    /**
     * Class constructor with the exit boolean set to the default value of <code>false</code>.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false);
    }

    /**
     * Returns the message to the user wrapped within this object.
     *
     * @return A string representing the response for the user.
     */
    public String getFeedback() {
        return feedbackToUser;
    }

    /**
     * Indicates whether or not to exit the chat bot.
     *
     * @return <code>true</code> if the command signals exiting the chat bot;
     *         <code>false</code> otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

}
