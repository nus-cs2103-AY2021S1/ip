public class Response {
    protected String response;
    public static final String indentation = "   ";
    public static final String underscore =
            "  _____________________________________________________________" + "\n";

    public Response(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return underscore + indentation + this.response + "\n" + underscore;
    }
}
