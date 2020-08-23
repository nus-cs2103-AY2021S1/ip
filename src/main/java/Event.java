import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {

    //Store the variables
    private String at = null;
    private Date date = null;
    private static final Pattern DATEMATCH =
            Pattern.compile("^(.*) (/at) (.*)$");
    private static DateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm");

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

    /**
     * @return date Date of the event
     */
    //Getter for the date of the
    public String getDate() {
        if (at != null){
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
        return String.format("%s /at %s", getMessage(), getDate());
    }

    public static Task parseCommand(final String args) {

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
            return new Event(name, date1);
        } catch (ParseException e){

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
