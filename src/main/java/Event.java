public class Event extends Task{

    //Store the variables
    protected String at;

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
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", this.getDate());
    }
}
