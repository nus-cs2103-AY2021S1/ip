package chatterbox;

public class ChatterboxException extends Exception {
    private final String msg;

    public ChatterboxException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String toString() {
        return msg;
    }
}
