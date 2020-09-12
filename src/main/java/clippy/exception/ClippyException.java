package clippy.exception;

public class ClippyException extends Exception {
    private String msg;
    
    public ClippyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}