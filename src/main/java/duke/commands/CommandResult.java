package duke.commands;

/**
 * Represents the result of a command.
 */
public class CommandResult {

    private String feedbackToUser;
    private boolean isExit;

    /**
     * Constructor.
     *
     * @param feedbackToUser The feedback to user.
     * @param isExit Whether the program is exiting.
     */
    public CommandResult(String feedbackToUser, boolean isExit) {
        this.feedbackToUser = feedbackToUser;
        this.isExit = isExit;
    }

    /**
     * Gets the feedback to user.
     *
     * @return Feedback
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Gets whether the system is exiting.
     *
     * @return Whether the system is exiting.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Checks if the {@code feedbackToUser} is equals to another CommandResult's.
     *
     * @param obj The other object
     * @return Whether the feedback to user is equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommandResult)) {
            return false;
        } else {
            CommandResult other = (CommandResult) obj;
            return feedbackToUser.equals(other.feedbackToUser) && isExit == other.isExit;
        }
    }
}
