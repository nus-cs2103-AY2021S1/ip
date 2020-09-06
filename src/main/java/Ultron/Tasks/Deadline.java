package ultron.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Deadline extends Task {

    /**
     * Pattern string the regex for parsing the command.
     */
    private static final Pattern DATE_MATCH =
        Pattern.compile("^(.*) (/by) (.*)$");
    /**
     * Pattern string for the regex for the date.
     */
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
     * String to store the string time.
     */
    private String at = null;

    /**
     * String to store the date time.
     */
    private Date date = null;

    /**
     * A Deadline Task.
     *
     * @param description Description for the task.
     * @param by          The deadline for the task in string format.
     */
    public Deadline(final String description, final String by) {
        super(description);
        at = by;
    }

    /**
     * A Deadline Task.
     *
     * @param description Description for the task.
     * @param by          The deadline for the task in Date format.
     */
    public Deadline(final String description, final Date by) {
        super(description);
        date = by;
    }

    /**
     * Parses the command for deadline format.
     * @param args Arguments to Deadline task.
     * @return Deadline task.
     */
    public static Deadline parseCommand(final String args) {
        Matcher matcher = DATE_MATCH.matcher(args);
        boolean match = matcher.find();
        String name = matcher.group(NAME_INDEX);
        String date = matcher.group(DATE_INDEX);
        try {
            Date date1 = DATE_FORMAT.parse(date);
            return new Deadline(name, date1);
        } catch (ParseException e) {

            //Pass the 2 arguments into the function
            return new Deadline(name, date);
        }
    }

    /**
     * String representation of deadline.
     *
     * @return String representation of Deadline.
     */
    public String getDate() {
        return Objects.requireNonNullElseGet(at, () -> new SimpleDateFormat("dd-MM-yyyy HHmm").format(date));
    }

    @Override
    public String getType() {
        return "DEADLINE";
    }

    @Override
    public String getCommand() {
        return String.format("%s /by %s", getMessage(), getDate());
    }

    @Override
    public String toString() {
        return "[D]"
            + super.toString()
            + String.format(" (by: %s)", this.getDate());
    }
}
