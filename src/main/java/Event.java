public class Event extends Task {

    private String when = "";

    public Event(String name, String when) {
        super(name);
        this.when = when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.when + ")";
    }
}
