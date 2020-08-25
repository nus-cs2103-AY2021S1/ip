package exception;

public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String errorMessage = "    ____________________________________________________________\n"
                + "     ☹ OOPS!!! "
                + getMessage()
                + "\n"
                + "    ____________________________________________________________\n";

        return errorMessage;
    }
}
