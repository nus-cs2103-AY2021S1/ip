public class Event extends Task {
    private String dateTime;

    Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    Event(String name, Boolean isDone, String dateTime) {
        super(name, isDone);
        this.dateTime = dateTime;
    }
    
    public String encode() {
        return isDone
                ? String.format("E | 1 | %s | %s", name, dateTime)
                : String.format("E | 0 | %s | %s", name, dateTime);
    }
    
    @Override
    public String toString() {
        String dateTimeFormat = String.format(" (at: %s)", dateTime);
        return "[E]" + super.toString() + dateTimeFormat;
    }
}