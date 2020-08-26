package Duke.main;

/**
 * This class is to make response
 * to the user's input.
 */
public class Response {
    protected String response;

    /**
     * Initialize a Response.
     *
     * @param response A string which
     *                 is the content of
     *                 response.
     */
    public Response(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return this.response;
    }
}
