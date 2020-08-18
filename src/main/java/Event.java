public class Event extends Task {

    protected String dateAndOrTime;

    Event(String description, String dateAndOrTime) {
        super(description);
        this.dateAndOrTime = dateAndOrTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + dateAndOrTime + ")";
    }
}
