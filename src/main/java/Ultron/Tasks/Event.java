package ultron.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Event extends Task {
    
    /**
     * Store the regex for parsing the command for event.
     */
    private static final Pattern DATE_MATCH =
            Pattern.compile("^(.*) (/at) (.*)$");
    /**
     * Store the date format regex.
     */
    private static final DateFormat DATE_FORMAT =
            new SimpleDateFormat("dd-MM-yyyy HHmm");
    //Store the variables
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
     * @param description   Description of the Event
     * @param at            Date of the event as a string
     */
    public Event(final String description, final String at) {

        //Call the superclass constructor
        super(description);

        //Store the at variable
        this.at = at;
    }

    /**
     * Task Event.
     * @param description   Description of the Event
     * @param at            Date of the event as a Date object
     */
    //Constructor for the event class
    public Event(final String description, final Date at) {

        //Call the superclass constructor
        super(description);

        //Store the at variable
        this.date = at;
    }

    /**
     * Gets the date of the event.
     * @return date Date of the event
     */
    public String getDate() {
        if (at != null){
            //Return the date
            return at;
        } else {
            return new SimpleDateFormat("dd-MM-yyyy HHmm").format(date);
        }

    }

    /**
     * Gets the type of the event.
     * @return String type
     */
    @Override
    public String getType() {
        return "EVENT";
    }

    /**
     * Gets the command representation of the event.
     * @return String command
     */
    @Override
    public String getCommand() {
        return String.format("%s /at %s", getMessage(), getDate());
    }

    /**
     * Parse the commands to Event class.
     * @param args  Arguments for the Event task
     * @return  Event with the arguments parsed
     */
    public static Event parseCommand(final String args) {

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

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + String.format(" (at: %s)", this.getDate());
    }
}
