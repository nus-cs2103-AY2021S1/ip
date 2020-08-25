public class Event extends Task {

    public Event(String description, String by) {
        super(description);
        symbol = "[E]";
        this.by = by;
    }

    @Override
    public String toString() {
        return (getSymbol() + super.toString() + " (at: " + getDate() + ")");
    }
}
