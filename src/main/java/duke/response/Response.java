package duke.response;

public class Response {
    private boolean isErrorMessage;
    private String responseMessage;
    private boolean isExit;

    /**
     * @param responseMessage response message to user's input.
     * @param isErrorMessage  checks if response message is an error message.
     * @param isExit          checks if response message is an exit message.
     */
    public Response(String responseMessage, boolean isErrorMessage, boolean isExit) {
        this.responseMessage = responseMessage;
        this.isErrorMessage = isErrorMessage;
        this.isExit = isExit;
    }

    public boolean isErrorMessage() {
        return isErrorMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public boolean isExit() {
        return isExit;
    }
}
