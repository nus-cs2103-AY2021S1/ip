public class Event extends Task {
    private String dateTime;

    Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String dateTimeFormat = String.format(" (at: %s)", dateTime);
        return "[E]" + super.toString() + dateTimeFormat;
    }
}