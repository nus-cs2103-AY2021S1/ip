public class Response {
    private String inputMsg;
    private boolean isExit = false;

    /**
     * Instantiates Response object with input message.
     *
     * @param inputMsg Message to output.
     * @return Response object
     */
    public Response(String inputMsg) {
        this.inputMsg = inputMsg;
    }

    /**
     * Instantiates Response object with input message and exit check.
     *
     * @param inputMsg Message to be delivered.
     * @param exitCheck Check to exit program or not.
     * @return Response object
     */
    public Response(String inputMsg, boolean exitCheck) {
        this.inputMsg = inputMsg;
        this.isExit = exitCheck;
    }

    /**
     * Determines if Response object is to exit or not.
     *
     * @return Boolean True if we want to exit, Boolean False if we do not want to exit.
     */
    public boolean exitChecker() {
        return isExit;
    }

    /**
     * Gets message that needs to be output.
     *
     * @return String message that needs to be printed.
     */
    public String getInputMessage() {
        return inputMsg;
    }
}
