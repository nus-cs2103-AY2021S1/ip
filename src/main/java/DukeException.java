public class DukeException extends Exception {
    private String exception;

    public DukeException(String exception) {
        this.exception = exception;
    }

    @Override
    public String getMessage() {
        return this.exception;
    }

    @Override
    public String toString() {
        return "DukeException[" + this.exception + "]";
    }
}