package ultron.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Deadline extends Task {

    /**
     * Pattern string the regex for parsing the command.
     */
    private static final Pattern DATE_MATCH =
            Pattern.compile("^(.*) (/by) (.*)$");
    private static final DateFormat DATE_FORMAT =
            new SimpleDateFormat("dd-MM-yyyy HHmm");
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
     * @param description   Description for the task
     * @param by            The deadline for the task in string format
     */
    //Constructor for the event class
    public Deadline(final String description, final String by) {

        //Call the superclass constructor
        super(description);

        //Store the at variable
        at = by;
    }

    /**
     * A Deadline Task.
     * @param description   Description for the task
     * @param by            The deadline for the task in Date format
     */
    //Constructor for the event class
    public Deadline(final String description, final Date by) {

        //Call the superclass constructor
        super(description);

        //Store the at variable
        date = by;
    }

    public static Task parseCommand(final String args) {
        //Create the matcher
        Matcher matcher = DATE_MATCH.matcher(args);

        //Check for matches
        matcher.find();

        //Get the date and the name
        String name = matcher.group(1);
        String date = matcher.group(3);

        //Extract the date
        try {

            //Parse the date
            Date date1 = DATE_FORMAT.parse(date);

            //Pass the date to the constructor
            return new Deadline(name, date1);
        } catch (ParseException e) {

            //Pass the 2 arguments into the function
            return new Deadline(name, date);
        }

    }

    /**
     * String representation of deadline.
     * @return String representation of Deadline
     */
    //Getter for the date of the
    public String getDate() {
        if (at != null) {
            //Return the date
            return this.at;
        } else {
            return new SimpleDateFormat("dd-MM-yyyy HHmm").format(date);
        }

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
