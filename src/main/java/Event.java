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
