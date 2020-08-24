package ultron.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Event extends Task {

    private static final Pattern DATE_MATCH =
            Pattern.compile("^(.*) (/at) (.*)$");
    private static final DateFormat DATE_FORMAT =
            new SimpleDateFormat("dd-MM-yyyy HHmm");
    //Store the variables
    private String at = null;
    private Date date = null;

    //Constructor for the event class
    public Event(final String description, final String at) {

        //Call the superclass constructor
        super(description);

        //Store the at variable
        this.at = at;
    }

    //Constructor for the event class
    public Event(final String description, final Date at) {

        //Call the superclass constructor
        super(description);

        //Store the at variable
        this.date = at;
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
            return new Event(name, date1);
        } catch (ParseException e) {

            //Pass the 2 arguments into the function
            return new Event(name, date);
        }


    }

    /**
     * @return date Date of the event
     */
    //Getter for the date of the
    public String getDate() {
        if (at != null) {
            //Return the date
            return at;
        } else {
            return new SimpleDateFormat("dd-MM-yyyy HHmm").format(date);
        }

    }

    @Override
    public String getType() {
        return "EVENT";
    }

    @Override
    public String getCommand() {
        return super.getCommand()
                + String.format("%s /at %s", getMessage(), getDate());
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + String.format(" (at: %s)", this.getDate());
    }
}
