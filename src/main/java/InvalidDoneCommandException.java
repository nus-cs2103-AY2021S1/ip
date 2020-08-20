public class InvalidDoneCommandException extends IllegalStateException {
    public InvalidDoneCommandException(String errorMsg) {
        super(errorMsg);
    }
}
