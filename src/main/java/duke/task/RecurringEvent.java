package duke.task;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.InvalidSaveException;
import duke.datetime.FastForwarder;
import duke.datetime.FastForwarderGenerator;

/**
 * Encapsulates a recurring event.
 * Recurring tasks assumes that the user frequently closes and reopens this app.
 * Upon initialization, Duke will read from the save and interpret the summaries and load them into storage.
 * During this process, for recurring tasks, if the end timing has passed, the task is pushed forward by the
 * specified interval.
 */
public class RecurringEvent extends Event {

    /** Symbol representing a Recurring Event object */
    public static final String SYMBOL = "R" + Event.SYMBOL;
    /** Number of separate fields in a event save summary */
    private static final int NUM_FIELDS_SUMMARY = 5;

    /** FastForwarder that controls the amount of time the start and end timings jump on each reoccurrence */
    private FastForwarder fastForwarder;
    /** Details used to create the FastForwarder object */
    private String recurringDetails;

    /**
     * Constructs a RecurringEvent instance with the given description, start and end timings and recurring details.
     *
     * @param eventDescription description of the task and the start and end timings of the event.
     * @param recurringDetails string represneting the length between each reoccurrence.
     * @throws DukeException if the description of recurring details are invalid.
     */
    public RecurringEvent(String eventDescription, String recurringDetails) throws DukeException {
        super(eventDescription);
        parseRecurringDetails(recurringDetails);
    }

    /** Constructs a recurring event instance directly from string arguments of each attribute */
    protected RecurringEvent(String eventDescription, String eventTiming, String recurringDetails)
            throws DukeException {
        super(eventDescription, eventTiming);
        parseRecurringDetails(recurringDetails);
    }

    /** Constructs a recurring event instance directly from the provided description and start and end timings */
    protected RecurringEvent(String eventDescription, LocalDateTime startTiming,
             LocalDateTime endTiming, String recurringDetails) {
        super(eventDescription, startTiming, endTiming);
        this.recurringDetails = recurringDetails.toUpperCase();
    }

    /** Parses the recurring details into a FastForwarder and saves it in the instance */
    private void parseRecurringDetails(String recurringDetails) throws DukeException {
        this.recurringDetails = recurringDetails;
        fastForwarder = FastForwarderGenerator.generateFastForwarder(recurringDetails);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Recurring: %s)", recurringDetails);
    }

    @Override
    public String getSummary() {
        return "R" + super.getSummary() + SYMBOL_SEPARATOR + recurringDetails;
    }

    /**
     * Returns a RecurringEvent instance from the summary provided.
     * Summary should be of the form RE|{0 or 1}|{description}|{event start and end timings}|{recurring details}
     *
     * @param summary summary from the save file.
     * @return Recurring event object corresponding to the summary provided.
     * @throws InvalidSaveException if the summary is invalid.
     */
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

            RecurringEvent updatedRecurringEvent = updateRecurringEvent(recurringEvent, isDone);

            return updatedRecurringEvent;
        } catch (DukeException e) {
            throw new InvalidSaveException("Invalid datetime format in save!");
        }
    }

    /** Returns a recurring event object with the event start and end timings updated to after the current time */
    private static RecurringEvent updateRecurringEvent(RecurringEvent recurringEvent, boolean isDone) {
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
            recurringEvent = new RecurringEvent(recurringEvent.getTaskDescription(),
                    startTime, endTime, recurringEvent.recurringDetails);
        }
        if (!isUpdated && isDone) {
            recurringEvent.markDone();
        }

        return recurringEvent;
    }



}
