package duke.exceptions;

public class DateSequenceException extends DukeException {
    public DateSequenceException() {
        this("The start date should not be later than end date");
    }

    public DateSequenceException(String errorMessage) {
        super(errorMessage);
    }
}
