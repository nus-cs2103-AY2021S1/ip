package junimo.ui;

/**
 * Represents a response to be displayed to the user as a message from Junimo.
 */
public class Response {
    private String messsage;
    private Boolean isErrorMessage;

    /**
     * Constructs an instance of Response.
     * @param messsage The response message.
     * @param isErrorMessage Whether the message is an error meessage or not.
     */
    public Response(String messsage, Boolean isErrorMessage) {
        this.messsage = messsage;
        this.isErrorMessage = isErrorMessage;
    }

    /**
     * Returns the response message.
     * @return The response message.
     */
    public String getMesssage() {
        return this.messsage;
    }

    /**
     * Returns whether or not the response message is an error message.
     * @return Whether or not the response message is an error message.
     */
    public Boolean getIsErrorMessage() {
        return this.isErrorMessage;
    }
}
