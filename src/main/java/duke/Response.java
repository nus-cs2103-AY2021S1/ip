package duke;

public class Response {

    private boolean isExit;
    private String responseMessage;

    Response(boolean isExit, String responseMessage) {
        this.isExit = isExit;
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public boolean shouldExit() {
        return isExit;
    }
}
