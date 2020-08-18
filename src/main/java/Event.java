public class Event extends Task {
    private String dateInfo;

    public Event(String description, String dateInfo) {
        super(description);
        this.dateInfo = dateInfo;
    }

    @Override
    public String toString() {
        return printCompletionFlag() + " | E | " + description + " | At: " + dateInfo;
    }
}
