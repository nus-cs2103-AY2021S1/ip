package duckie.exception;

public class DuckieException extends Exception {
    public DuckieException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
