package Duke.main;

public class Response {
    protected String response;

    public Response(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return this.response;
    }
}
