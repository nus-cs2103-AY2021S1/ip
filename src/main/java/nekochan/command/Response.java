package nekochan.command;

/**
 * The {@code Response} class represents a response of an executed {@link Command}.
 */
public class Response {

    private boolean isExit;
    private String responseMessage;

    /**
     * Constructs a {@code Response} with the specified {@code responseMessage} and
     * {@code isExit} value.
     *
     * @param isExit true if the {@code Command} executed was the {@link ExitCommand}.
     * @param responseMessage the feedback message from the {@code Command}.
     */
    public Response(boolean isExit, String responseMessage) {
        this.isExit = isExit;
        this.responseMessage = responseMessage;
    }

    /**
     * Returns the message stored in this {@code Response} object.
     *
     * @return the message stored in this {@code Response} object.
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Returns true if the {@code Command} executed was the {@code ExitCommand}.
     *
     * @return true if the {@code Command} executed was the {@code ExitCommand}.
     */
    public boolean shouldExit() {
        return isExit;
    }
}
