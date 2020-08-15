public class NoSpaceException extends InvalidTaskException {
    public NoSpaceException(String type, String task) {
        super("Do you mean " + type + " " + task);
    }
}
