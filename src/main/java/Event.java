import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task{

    //Store the variables
    protected final String at;
    private static final Pattern date_match = Pattern.compile("^(.*) (/at) (.*)$");

    //Constructor for the event class
    public Event(String description, String at){

        //Call the superclass constructor
        super(description);

        //Store the at variable
        this.at = at;
    }

    //Getter for the date of the
    public String getDate(){

        //Return the date
        return this.at;
    }

    @Override
    public String getType() {
        return "EVENT";
    }

    @Override
    public String getCommand() {
        return String.format("%s /at %s", getMessage(), getDate());
    }

    public static Task parseCommand(String args) {
        //Create the matcher
        Matcher matcher = date_match.matcher(args);

        //Check for matches
        matcher.find();

        //Get the date and the name
        String name = matcher.group(1);
        String date = matcher.group(3);

        //Pass the 2 arguments into the function
        return new Event(name, date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", this.getDate());
    }
}
