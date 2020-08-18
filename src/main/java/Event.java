public class Event extends Task{
    public String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + time + ")";
    }
}
