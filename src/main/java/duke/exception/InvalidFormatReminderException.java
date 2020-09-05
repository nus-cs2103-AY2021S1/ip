package duke.exception;

public class InvalidFormatReminderException extends DukeException {
    private static final String REMINDER_EXCEPTION = " ☹ OOPS! A proper remind format would be like, "
            + "e.g. remind \'index in task list\' \'y or n\' ";
    public InvalidFormatReminderException() {
        super(REMINDER_EXCEPTION);
    }
}
