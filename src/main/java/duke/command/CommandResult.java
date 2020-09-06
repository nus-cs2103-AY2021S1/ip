package duke.command;

public class CommandResult {
    private String feedbackToUser;
    private boolean isExit = false;

    /**
     * Creates a CommandResult object, with the output for the user as the argument
     *
     * @param feedbackToUser The message for the user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * The command result object will be from the ExitCommand
     */
    public void setIsExit() {
        isExit = true;
    }

    /**
     * Checks if the command result was returned from an ExitCommand
     *
     * @return A boolean whether this was from an ExitCommand
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Gets the string for user to see from the execution of a command
     *
     * @return The string for the user to see
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
