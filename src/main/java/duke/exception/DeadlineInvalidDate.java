package duke.exception;

/** An exception for an invalid date for a deadline. */
public class DeadlineInvalidDate extends InvalidDateTimeException {

    /** Constructs a DeadlineInvalidDate exception. */
    public DeadlineInvalidDate() {
        super("OOPS. You need to put \"/by [DateTimeFormat]\"\n"
            + "after a duke.task.Deadline.");
    }
}
