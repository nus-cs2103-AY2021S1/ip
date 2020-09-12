package clippy.exception;

public class ClippyException extends Exception {
    public ClippyException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}