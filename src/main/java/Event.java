public class Event extends Task {

    private String dateTime;

    public Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.getItemName() + "(at: " + dateTime + ")";
    }
}
