public class ClippyException extends Exception {
    private String msg;
    
    ClippyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}