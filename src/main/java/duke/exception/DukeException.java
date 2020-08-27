package duke.exception;

public class DukeException extends Exception {
    private final String e;

    public DukeException() {
        this.e = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public DukeException(String e) {
        this.e = "☹ OOPS!!! " + e;
    }

    @Override
    public String toString() {
        return e;
    }
}
