package shiro.command;

/**
 * a class representing the result given by a command
 */
public class CommandResult {
    String feedbackToUser;
    boolean isExit;

    /**
     * class constructor with the isExit parameter set to false
     * @param feedbackToUser the command result represented as a string that will be displayed to the user
     */
    CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.isExit = false;
    }

    /**
     * class constructor with the option to set isExit to either true or false
     * @param feedbackToUser the command result represented as a string that will be displayed to the user
     * @param isExit a boolean value indicating if the command result should cause the app to exit
     */
    CommandResult(String feedbackToUser, boolean isExit) {
        this.feedbackToUser = feedbackToUser;
        this.isExit = isExit;
    }

    /**
     * returns the string representation of the command result
     * @return the command result as a string
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * returns a boolean value indicating if the command result should cause the app to exit
     * @return true if the command result causes the app to exit and false otherwise
     */
    public boolean isExit() {
        return isExit;
    }
}