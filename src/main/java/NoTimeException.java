public class NoTimeException extends InvalidTaskException {
    public NoTimeException(String type) {
        super("\u2639 OOPS!!! Time should be specified for " + type);
    }
}
