/**
 * Event class is a subclass of Task which holds information of an Event
 * with an occuring time and date.
 */
public class Event extends Task{

    protected DateAndTime at;

    public Event(String description, DateAndTime at){
        super(description);
        this.at = at;
    }

    public Event(String description, DateAndTime at, boolean isDone){
        super(description, isDone);
        this.at = at;
    }


    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
