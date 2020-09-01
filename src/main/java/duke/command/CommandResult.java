package duke.command;

public class CommandResult {
    private String feedbackToUser;
    private boolean isExit = false;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public void setIsExit() {
        isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
