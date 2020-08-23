public class Event extends Task {
    private String date;

    Event(String description, String date) {
        super(description);
        this.date = date;
    }

    Event(String description, String date, String completionStatus) {
        super(description, completionStatus);
        this.date = date;
    }

    @Override
    String getType() {
        return "E";
    }

    @Override
    String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + date + ")";
    }
}
