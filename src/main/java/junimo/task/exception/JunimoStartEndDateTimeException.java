package junimo.task.exception;

public class JunimoStartEndDateTimeException extends IllegalArgumentException {
    public JunimoStartEndDateTimeException() {
        super("OOPS! Start date and time has to be before the end date and time!");
    }
}
