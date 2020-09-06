package duke.command;

public class CommandResult {
    String feedbackToUser;
    boolean isExit;

    CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.isExit = false;
    }

    CommandResult(String feedbackToUser, boolean isExit) {
        this.feedbackToUser = feedbackToUser;
        this.isExit = isExit;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isExit() {
        return isExit;
    }
}