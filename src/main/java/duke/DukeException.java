package duke;

public class DukeException extends Exception {

    DukeException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "\uD83D\uDE40" + "OOPS!!! " + super.toString();
    }

}
