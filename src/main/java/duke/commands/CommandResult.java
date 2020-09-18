package duke.commands;

public class CommandResult {
    private final String messageToUser;

    public CommandResult(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    /**
     * Getter for messageToUser.
     * @return message to be printed for the user.
     */
    public String getMessageToUser() {
        assert !messageToUser.trim().equals("");
        return messageToUser;
    }

}
