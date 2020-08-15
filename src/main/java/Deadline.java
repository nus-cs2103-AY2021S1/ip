public class Deadline extends Task{

    protected String date;
    public Deadline(String description, String date){

        //Call the superclass
        super(description);

        //Store the date
        this.date = date;
    }

    public String getDate(){

        //Return the date
        return this.date;
    }

    @Override
    public String toString() {

        //Add the additional attributes on top of the original toString method
        return "[D]" + super.toString() + String.format(" (by: %s)", this.getDate());
    }
}