package com.siawsam.duke;

/**
 * Represents a response from a {@link Duke duke} operation.
 */
public class Response {
    private String message = "";
    private boolean isEmpty = false;
    private boolean isTerminating = false;
    
    /**
     * Constructs a new Response object with the given message.
     *
     * @param message The response's message.
     */
    public Response(String message) {
        this.message = message;
    }
    
    private Response(String message, boolean isTerminating) {
        this.message = message;
        this.isTerminating = isTerminating;
    }
    
    private Response(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    
    /**
     * Constructs and returns an empty Response object.
     *
     * @return The empty Response.
     */
    public static Response emptyResponse() {
        return new Response(true);
    }
    
    /**
     * Constructs and returns a response that terminates the running Duke instance.
     *
     * @param message The response's message.
     * @return The terminating response.
     */
    public static Response terminatingResponse(String message) {
        return new Response(message, true);
    }
    
    /**
     * Returns the message of a response.
     *
     * @return The message of the response.
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Returns true for a terminating response.
     *
     * @return True for a terminating response, false if otherwise.
     */
    public boolean isTerminating() {
        return isTerminating;
    }
    
    /**
     * Returns true for an empty response.
     *
     * @return True for an empty response, false if otherwise.
     */
    public boolean isEmpty() {
        return isEmpty;
    }
}
