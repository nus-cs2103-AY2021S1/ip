package duke.task;

import duke.DukeException;
import duke.InvalidSaveException;
import duke.datetime.FastForwarder;
import duke.datetime.FastForwarderGenerator;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Encapsulates a recurring deadline.
 * Recurring tasks assumes that the user frequently closes and reopens this app.
 * Upon initialization, Duke will read from the save and interpret the summaries and load them into storage.
 * During this process, for recurring tasks, if the end timing has passed, the task is pushed forward by the
 * specified interval.
 */
public class RecurringDeadline extends Deadline {

    /** Symbol representing a Recurring Deadline object. */
    public static final String SYMBOL = "R" + Deadline.SYMBOL;
    /** Number of separate fields in a recurring deadline save summary */
    private static final int NUM_FIELDS_SUMMARY = 5;

    /** FastForwarder that controls the amount of time the deadline jumps on each reoccurrence */
    private FastForwarder fastForwarder;
    /** Details used to create the FastForwarder object */
    private String recurringDetails;

    /**
     * Constructs a RecurringDeadline instance with the given description, timing and recurring details.
     *
     * @param deadlineDescription description of the task and the deadline.
     * @param recurringDetails string representing the length between each reoccurrence.
     * @throws DukeException if the description or recurring details are invalid.
     */
    public RecurringDeadline(String deadlineDescription, String recurringDetails) throws DukeException {
        super(deadlineDescription);
        parseRecurringDetails(recurringDetails);
    }

    /** Constructs a RecurringDeadline instance directly from string arguments of each attribute */
    protected RecurringDeadline(String deadlineDescription, String deadlineTiming, String recurringDetails)
            throws DukeException {
        super(deadlineDescription, deadlineTiming);
        parseRecurringDetails(recurringDetails);
    }

    /** Constructs a RecurringDeadline instance directly from the provided description and deadline */
    protected RecurringDeadline(String deadlineDescription, LocalDateTime deadline, String recurringDetails) {
        super(deadlineDescription, deadline);
        this.recurringDetails = recurringDetails.toUpperCase();
    }

    /** Parses the recurring details into a FastForwarder and saves it in the instance */
    private void parseRecurringDetails(String recurringDetails) throws DukeException {
        this.recurringDetails = recurringDetails;
        fastForwarder = FastForwarderGenerator.generateFastForwarder(recurringDetails);
    }


    @Override
    public String toString() {
        return super.toString() + String.format(" (Interval: %s)", recurringDetails);
    }

    @Override
    public String getSummary() {
        return "R" + super.getSummary() + SYMBOL_SEPARATOR + recurringDetails;
    }

    /**
     * Returns a RecurringDeadline instance from the summary provided.
     * Summary should be of the form RD|{0 or 1}|{description}|{deadline}|{recurring details}.
     *
     * @param summary summary from the save file.
     * @return Recurring deadline object corresponding to the summary provided.
     * @throws InvalidSaveException if the summary is invalid.
     */
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

    /** Returns a recurring deadline object with the deadline updated to after the current time */
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
