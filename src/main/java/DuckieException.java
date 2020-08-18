public class DuckieException extends Exception {
    DuckieException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
