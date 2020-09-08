package duke.task;

import duke.DukeException;
import duke.InvalidSaveException;
import duke.datetime.FastForwarder;
import duke.datetime.FastForwarderGenerator;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class RecurringEvent extends Event {

    public static final String SYMBOL = "R" + Event.SYMBOL;
    /** Number of separate fields in a event save summary */
    private static final int NUM_FIELDS_SUMMARY = 5;

    private FastForwarder fastForwarder;
    private String recurringDetails;

    public RecurringEvent(String eventDescription, String recurringDetails) throws DukeException {
        super(eventDescription);
        parseRecurringDetails(recurringDetails);
    }

    protected RecurringEvent(String eventDescription, String eventTiming, String recurringDetails)
            throws DukeException {
        super(eventDescription, eventTiming);
        parseRecurringDetails(recurringDetails);
    }

    protected RecurringEvent(String eventDescription, LocalDateTime startTiming,
             LocalDateTime endTiming, String recurringDetails) {
        super(eventDescription, startTiming, endTiming);
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

    public static RecurringEvent reconstructFromSummary(String summary) throws InvalidSaveException {
        String[] details = summary.split(Pattern.quote(SYMBOL_SEPARATOR));
        if (details.length != NUM_FIELDS_SUMMARY) {
            throw new InvalidSaveException("Wrong number of details!");
        } else if (!isValidSaveSymbol(details[1])) {
            throw new InvalidSaveException("Invalid completion status! Ensure that it is either 0 or 1");
        }

        try {
            RecurringEvent recurringEvent = new RecurringEvent(details[2], details[3], details[4]);
            boolean isDone = details[1].equals(SYMBOL_DONE);

            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime startTime = recurringEvent.getStartTiming();
            LocalDateTime endTime = recurringEvent.getEndTiming();
            boolean isOutdated = endTime.compareTo(currentTime) < 0;
            while (isOutdated) {
                FastForwarder fastForwarder = recurringEvent.fastForwarder;
                startTime = fastForwarder.apply(startTime);
                endTime = fastForwarder.apply(endTime);
                isOutdated = endTime.compareTo(currentTime) < 0;
            }

            boolean isUpdated = endTime != recurringEvent.getEndTiming();
            if (isUpdated) {
                recurringEvent = new RecurringEvent(details[2], startTime, endTime, details[4]);
            }
            if (!isUpdated && isDone) {
                recurringEvent.markDone();
            }
            return recurringEvent;
        } catch (DukeException e) {
            throw new InvalidSaveException("Invalid datetime format in save!");
        }
    }



}
