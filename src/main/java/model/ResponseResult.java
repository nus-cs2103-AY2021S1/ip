package model;

/**
 * Links a command result message and whether the message is an
 * error message or not in order to facilitate the different expressions
 * of DukeBunny images in the GUI.
 */
public class ResponseResult {
    private boolean isError;
    private String msg;

    /**
     * Constructs a response result which contains a boolean and a string.
     * @param isError whether the command message is an error or not.
     * @param msg command result message
     */
    public ResponseResult(boolean isError, String msg) {
        this.isError = isError;
        this.msg = msg;
    }

    public boolean isError() {
        return isError;
    }

    public String getMsg() {
        return msg;
    }
}
