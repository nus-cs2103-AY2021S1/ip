public class DukeException extends Exception {
    private String msg;
    DukeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
