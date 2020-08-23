public class DateTimeException extends DukeException {
    DateTimeException() {

    }

    public String toString() {
        return "Oops! Please check the input of your date. It has to be in the format: YYYY-MM-DD";
    }
}
