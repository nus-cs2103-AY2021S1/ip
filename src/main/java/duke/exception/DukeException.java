package duke.exception;

public class DukeException extends Exception{
    public String msg;

    public DukeException() {
    }

    public DukeException(String msg) {
        this.msg = msg;
    }
}
