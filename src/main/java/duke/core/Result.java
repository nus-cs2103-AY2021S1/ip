package duke.core;

/**
 * The Result class represents the result of the execution of a command.
 */
public class Result {
    private String message;
    private boolean isContinuing;
    private MessageType messageType;

    /**
     * Returns a result with the message,
     * the boolean value of whether the process should continue, and
     * the type of the message.
     *
     * @param message The message of the result
     * @param isContinuing The boolean value of whether the process should continue.
     * @param messageType The type of the message.
     */
    public Result(String message, boolean isContinuing, MessageType messageType) {
        this.message = message;
        this.isContinuing = isContinuing;
        this.messageType = messageType;
    }

    /**
     * Returns the message that needs to be shown.
     *
     * @return The message that needs to be shown.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the value of whether the process should continue.
     *
     * @return The value of whether the process should continue.
     */
    public boolean isContinuing() {
        return isContinuing;
    }

    /**
     * Returns the type of the message.
     *
     * @return The type of the message.
     */
    public MessageType getMessageType() {
        return messageType;
    }
}
