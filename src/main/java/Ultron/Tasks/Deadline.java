package ultron.tasks;

import java.util.Date;
import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Deadline extends Task {

    //Store the variables
    /**
     * String to store the string time.
     */
    private String at = null;

    /**
     * String to store the date time.
     */
    private Date date = null;

    /**
     * Pattern string the regex for parsing the command.
     */
    private static final Pattern DATEMATCH =
            Pattern.compile("^(.*) (/by) (.*)$");
    private static final DateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm");

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

    /**
     * Get the date of the deadline.
     * @return date Date of the event
     */
    //Getter for the date of the
    public String getDate() {
        if (at != null){
            //Return the date
            return this.at;
        } else {
          return new SimpleDateFormat("dd-MM-yyyy HHmm").format(date);
        }

    }

    /**
     * Return the Type of Task as a string.
     * @return  String type
     */
    @Override
    public String getType() {
        return "DEADLINE";
    }

    /**
     * Returns the task as a command string.
     * @return String command
     */
    @Override
    public String getCommand() {
        return String.format("%s /by %s", getMessage(), getDate());
    }

    /**
     * Parse the command for the deadline class.
     * @param args arguments given for the command
     * @return  Deadline with the arguments parsed
     */
    public static Deadline parseCommand(final String args) {
        //Create the matcher
        Matcher matcher = DATEMATCH.matcher(args);

        //Check for matches
        matcher.find();

        //Get the date and the name
        String name = matcher.group(1);
        String date = matcher.group(3);

        //Extract the date
        try {

            //Parse the date
            Date date1 = format.parse(date);

            //Pass the date to the constructor
            return new Deadline(name, date1);
        } catch (ParseException e){

            //Pass the 2 arguments into the function
            return new Deadline(name, date);
        }

    }

    /**
     * String representation of deadline.
     * @return String representation of Deadline
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + String.format(" (by: %s)", this.getDate());
    }
}
