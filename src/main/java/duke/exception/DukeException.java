package duke.exception;
public class DukeException extends Exception {
    private String msg;
    public DukeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
