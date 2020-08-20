public class Event extends Task{

    private String dateTime;

    public Event(String description) throws DukeException {
        super(description.substring(5),"event");
        this.dateTime = description.substring(description.indexOf("/")+4);
        this.setType("event");

    }

    @Override
    public String toString() {
        return "[E]" + super.toString()+" (at: " + dateTime + ")";
    }


}
