public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String done, String description, String at) {
        super(description);
        this.at = at;
        this.isDone = done.equals("1");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
    
    @Override
    public String saveString() {
        return "E" + super.saveString() + ", " + this.at;
    }
}
