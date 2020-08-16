import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task{

    //Store the variables
    protected String at;
    private static Pattern date_match = Pattern.compile("^(.*) (/by) (.*)$");

    //Constructor for the event class
    public Deadline(String description, String at){

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

    public static Task parseCommand(String args) {
        //Create the matcher
        Matcher m = date_match.matcher(args);

        //Check for matches
        m.find();

        //Get the date and the name
        String name = m.group(1);
        String date = m.group(3);

        //Pass the 2 arguments into the function
        return new Deadline(name, date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.getDate());
    }
}
