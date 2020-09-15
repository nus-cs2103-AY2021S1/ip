package commands;

public class CommandResult {
    private final String messageToUser;
    private final boolean isExit;

    public CommandResult(String messageToUser) {
        this.messageToUser = messageToUser;
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public String getMessageToUser() {
        assert !messageToUser.trim().equals("");
        return messageToUser;
    }

}
