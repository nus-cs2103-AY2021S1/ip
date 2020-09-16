package commands;

public class CommandResult {
    private final String messageToUser;

    public CommandResult(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    public String getMessageToUser() {
        assert !messageToUser.trim().equals("");
        return messageToUser;
    }

}
