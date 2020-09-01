package duke;

/**
 * A class to encapsulate response information
 * before returning to handler.
 */
public class ResultInfo {

    private boolean isSuccess;
    private boolean isExit;
    private String response;

    /**
     * A public constructor for resultinfo
     * @param isSuccess Whether the command is executed successfully
     * @param isExit Whether the command is recognised as exit command
     * @param response Response message
     */
    public ResultInfo(boolean isSuccess, boolean isExit, String response) {
        this.isSuccess = isSuccess;
        this.isExit = isExit;
        this.response = response;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
