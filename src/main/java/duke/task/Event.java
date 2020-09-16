package duke.task;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.InvalidSaveException;
import duke.datetime.DateTimeHandler;
import duke.util.Pair;

/**
 * Encapsulates an Event.
 * Event are tasks that we have to attend at a given time slot.
 */
public class Event extends Task {

    /** Symbol representing the type of Task this is */
    public static final String SYMBOL = "E";
    /** String separator used to separate the task description and the timing */
    public static final String SPLITTER = " /at ";
    /** Number of separate fields in a event save summary */
    private static final int NUM_FIELDS_SUMMARY = 4;
    /** Number of separate fields required to create a event task */
    private static final int NUM_FIELDS_DESCRIPTION = 2;

    /** Timing of the start of event */
    private LocalDateTime startTiming;
    /** Timing of the end of event */
    private LocalDateTime endTiming;

    /**
     * Creates an Event object with the given event description and timing.
     *
     * @param taskDescription full description of the event including task and timing.
     * @throws DukeException if the format of the description is invalid.
     */
    public Event(String taskDescription) throws DukeException {
        super(taskDescription.split(SPLITTER)[0]);
        String[] details = taskDescription.split(SPLITTER);
        if (details.length != NUM_FIELDS_DESCRIPTION) {
            throw new DukeException("Please follow the format of \"{task} /at {event timing}\"");
        }
        processEventTimingString(taskDescription.split(SPLITTER)[1]);
    }

    /**
     * Creates a new Event object by manually setting the description and timing
     *
     * @param eventDescription description of the event task only.
     * @param timing string description of the timing of the event.
     * @throws DukeException if the timing provided for the event has an invalid format.
     */
    protected Event(String eventDescription, String timing) throws DukeException {
        super(eventDescription);
        processEventTimingString(timing);
    }

    protected Event(String eventDescription, LocalDateTime startTiming, LocalDateTime endTiming) {
        super(eventDescription);
        this.startTiming = startTiming;
        this.endTiming = endTiming;
    }

    /**
     * Processes an event timing String by assigning the pair of start and end timings to the properties.
     *
     * @param timing String containing the details of the start and end timing.
     * @throws DukeException if the timing provided for the event has an invalid format.
     */
    private void processEventTimingString(String timing) throws DukeException {
        Pair<LocalDateTime, LocalDateTime> pair =
                DateTimeHandler.parseEventTimings(timing);
        startTiming = pair.getFirst();
        endTiming = pair.getSecond();
    }

    /**
     * Returns the String representation of the object.
     * Updated from the superclass Task to include the type of Task this object is and include the timing.
     *
     * @return returns a String representing an Event with a "[E]" identifier and timing.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", SYMBOL, super.toString(), getTimingString());
    }


    /** Returns a string of the timings corresponding to this event */
    private String getTimingString() {
        if (startTiming.toLocalDate().equals(endTiming.toLocalDate())) {
            return DateTimeHandler.STANDARD_DATETIME_FORMAT.format(startTiming)
                    + "-" + DateTimeHandler.STANDARD_2400_FORMAT.format(endTiming.toLocalTime());
        } else {
            return DateTimeHandler.STANDARD_DATETIME_FORMAT.format(startTiming) + "-"
                    + DateTimeHandler.STANDARD_DATETIME_FORMAT.format(endTiming);
        }
    }

    /**
     * Returns a summary of the Event.
     *
     * @return string containing type, completion status, description and timing.
     */
    @Override
    public String getSummary() {
        return super.getSummary() + SYMBOL_SEPARATOR + getTimingString();
    }

    /**
     * Returns an Event object corresponding to the summary given.
     * Event summary has to be of the form "E|{0 or 1 representing completion}|{description}|{event duration}".
     *
     * @param summary string summary of the Event object to be reconstructed.
     * @return Event object representing the summary given.
     * @throws InvalidSaveException if the summary in the save file is invalid.
     */
    public static Event reconstructFromSummary(String summary) throws InvalidSaveException {
        String[] details = summary.split(Pattern.quote(SYMBOL_SEPARATOR));
        if (details.length != NUM_FIELDS_SUMMARY) {
            throw new InvalidSaveException("Wrong number of details!");
        } else if (!isValidSaveSymbol(details[1])) {
            throw new InvalidSaveException("Invalid completion status! Ensure that it is either 0 or 1");
        }

        try {
            Event event = new Event(details[2], details[3]);
            boolean isDone = details[1].equals(SYMBOL_DONE);
            if (isDone) {
                event.markDone();
            }
            return event;
        } catch (DukeException e) {
            throw new InvalidSaveException("Invalid datetime format in save!");
        }
    }

    /**
     * Returns 'E' representing an Event type.
     *
     * @return string "E".
     */
    @Override
    public String getSymbol() {
        return SYMBOL;
    }

    /**
     * Returns start timing of the event.
     *
     * @return start timing as a LocalDateTime object.
     */
    public LocalDateTime getStartTiming() {
        return startTiming;
    }

    /**
     * Returns end timing of the event.
     *
     * @return end timing as a LocalDateTime object.
     */
    public LocalDateTime getEndTiming() {
        return endTiming;
    }

}
