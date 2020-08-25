public class Event extends Task {

    protected String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }
    public String getTime(){
        return this.at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
