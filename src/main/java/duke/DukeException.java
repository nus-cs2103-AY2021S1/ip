package duke;

public class DukeException extends Exception {
    protected String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "********ERROR********\n" + "OOPS!!! " + this.message + "\n********ERROR********";
    }
}
