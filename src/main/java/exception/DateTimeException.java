package exception;

public class DateTimeException extends DukeException {
    public DateTimeException() {
    }

    public String toString() {
        return "Oops! Please check the input of your date. It has to be in the format: YYYY-MM-DD";
    }
}
