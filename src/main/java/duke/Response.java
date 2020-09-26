package duke;

/**
 * Returns the Response to the input given by user
 */
public class Response {
    private String output; //string output given by Duke
    private boolean isException; //tests whether the output given is an exception

    /**
     * Constructor that assigns respective values to
     *
     * @param response
     * @param isException
     */
    public Response(String response, boolean isException) {
        this.output = response;
        this.isException = isException;
    }

    /**
     * Returns String value of response
     *
     * @return value of output
     */
    public String getOutput() {
        return output;
    }

    /**
     * Returns value of isException
     *
     * @return boolean value of isException, which is true if Duke gives exception and false otherwise
     */
    public boolean getIsException() {
        return isException;
    }
}
