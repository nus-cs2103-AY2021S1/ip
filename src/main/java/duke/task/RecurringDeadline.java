package duke.task;

import duke.DukeException;
import duke.InvalidSaveException;
import duke.datetime.FastForwarder;
import duke.datetime.FastForwarderGenerator;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class RecurringDeadline extends Deadline {

    public static final String SYMBOL = "R" + Deadline.SYMBOL;
    /** Number of separate fields in a event save summary */
    private static final int NUM_FIELDS_SUMMARY = 5;

    private FastForwarder fastForwarder;
    private String recurringDetails;

    public RecurringDeadline(String deadlineDescription, String recurringDetails) throws DukeException {
        super(deadlineDescription);
        parseRecurringDetails(recurringDetails);
    }

    protected RecurringDeadline(String deadlineDescription, String deadlineTiming, String recurringDetails)
            throws DukeException {
        super(deadlineDescription, deadlineTiming);
        parseRecurringDetails(recurringDetails);
    }

    protected RecurringDeadline(String eventDescription, LocalDateTime deadline, String recurringDetails) {
        super(eventDescription, deadline);
        this.recurringDetails = recurringDetails.toUpperCase();
    }

    private void parseRecurringDetails(String recurringDetails) throws DukeException {
        this.recurringDetails = recurringDetails;
        fastForwarder = FastForwarderGenerator.generateFastForwarder(recurringDetails);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Recurring Interval: %s)", recurringDetails);
    }

    @Override
    public String getSummary() {
        return "R" + super.getSummary() + SYMBOL_SEPARATOR + recurringDetails;
    }

    public static RecurringDeadline reconstructFromSummary(String summary) throws InvalidSaveException {
        String[] details = summary.split(Pattern.quote(SYMBOL_SEPARATOR));
        if (details.length != NUM_FIELDS_SUMMARY) {
            throw new InvalidSaveException("Wrong number of details!");
        } else if (!isValidSaveSymbol(details[1])) {
            throw new InvalidSaveException("Invalid completion status! Ensure that it is either 0 or 1");
        }

        try {
            RecurringDeadline recurringDeadline = new RecurringDeadline(details[2], details[3], details[4]);
            boolean isDone = details[1].equals(SYMBOL_DONE);

            RecurringDeadline updatedRecurringDeadline = updateRecurringDeadline(recurringDeadline, isDone);

            return updatedRecurringDeadline;
        } catch (DukeException e) {
            throw new InvalidSaveException("Invalid datetime format in save!");
        }
    }

    private static RecurringDeadline updateRecurringDeadline(RecurringDeadline recurringDeadline, boolean isDone) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime deadline = recurringDeadline.getDeadline();
        boolean isOutdated = deadline.compareTo(currentTime) < 0;
        while (isOutdated) {
            FastForwarder fastForwarder = recurringDeadline.fastForwarder;
            deadline = fastForwarder.apply(deadline);
            isOutdated = deadline.compareTo(currentTime) < 0;
        }

        boolean isUpdated = deadline != recurringDeadline.getDeadline();
        if (isUpdated) {
            recurringDeadline = new RecurringDeadline(recurringDeadline.getTaskDescription(),
                    deadline, recurringDeadline.recurringDetails);
        }
        if (!isUpdated && isDone) {
            recurringDeadline.markDone();
        }

        return recurringDeadline;
    }

}
