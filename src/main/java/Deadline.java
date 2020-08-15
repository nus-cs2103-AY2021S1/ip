public class Deadline extends Task{

    //Store the variables
    protected String at;

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by:%s)", this.getDate());
    }
}
