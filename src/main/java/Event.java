public class Event extends Task {
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    private String dateTime;

    @Override
    public String getStatus() {
        return super.getStatus() + " (at: " + dateTime + ")";
    }
}
