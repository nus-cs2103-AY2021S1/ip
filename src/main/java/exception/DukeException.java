package exception;

public class DukeException extends Exception {
    DukeException(String msg) {
        super("Apologies, " + msg);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
