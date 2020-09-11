package duke.core;

public class Result {
    private String message;
    private boolean isContinuing;
    private MessageType messageType;

    /**
     * Returns a result with the message and
     * the boolean value of whether the process should continue.
     *
     * @param message The message of the result
     * @param isContinuing The boolean value of whether the process should continue.
     */
    public Result(String message, boolean isContinuing, MessageType messageType) {
        this.message = message;
        this.isContinuing = isContinuing;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public boolean isContinuing() {
        return isContinuing;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
