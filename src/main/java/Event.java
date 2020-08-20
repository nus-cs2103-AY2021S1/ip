// partial solution copied from iP
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description.trim());
        this.at = at.trim();
    }

    @Override
    public String toString() {
        return "  [E]" + super.toString() + " (at: " + at + ")";
    }
}
