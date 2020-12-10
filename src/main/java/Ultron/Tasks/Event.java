package ultron.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Event extends Task {
    /** Stores the regex for parsing the command for event. */
    private static final Pattern DATE_MATCH =
        Pattern.compile("^(.*) (/at) (.*)$");
    /** Stores the date format regex.*/
    private static final DateFormat DATE_FORMAT =
        new SimpleDateFormat("dd-MM-yyyy HHmm");
    /**
     * Index of the group to get the name of the task.
     */
    private static final int NAME_INDEX = 1;
    /**
     * Index of the data of the name of task.
     */
    private static final int DATE_INDEX = 3;
    /**
     * Store the String form for time of event.
     */
    private String at = null;

    /**
     * Store a date form for time of event.
     */
    private Date date = null;

    /**
     * Task event.
     *
     * @param description Description of the Event.
     * @param at          Date of the event as a string.
     */
    public Event(final String description, final String at) {
        super(description);
        this.at = at;
    }

    /**
     * Task Event.
     *
     * @param description Description of the Event.
     * @param at          Date of the event as a Date object.
     */
    public Event(final String description, final Date at) {
        super(description);
        this.date = at;
    }

    /**
     * Parse the commands to Event class.
     *
     * @param args Arguments for the Event task.
     * @return Event with the arguments parsed.
     */
    public static Event parseCommand(final String args) {
        Matcher matcher = DATE_MATCH.matcher(args);
        boolean match = matcher.find();
        assert match;
        String name = matcher.group(NAME_INDEX);
        String date = matcher.group(DATE_INDEX);
        try {
            Date date1 = DATE_FORMAT.parse(date);
            return new Event(name, date1);
        } catch (ParseException e) {
            return new Event(name, date);
        }
    }

    /**
     * Gets the date of the event.
     *
     * @return date Date of the event.
     */
    public String getDate() {
        return Objects.requireNonNullElseGet(at, () -> new SimpleDateFormat("dd-MM-yyyy HHmm").format(date));

    }

    /**
     * Gets the type of the event.
     *
     * @return String type.
     */
    @Override
    public String getType() {
        return "EVENT";
    }

    /**
     * Gets the command representation of the event.
     *
     * @return String command.
     */
    @Override
    public String getCommand() {
        return String.format("%s /at %s", getMessage(), getDate());
    }

    @Override
    public String toString() {
        return "[E]"
            + super.toString()
            + String.format(" (at: %s)", this.getDate());
    }
}
