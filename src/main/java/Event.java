public class Event extends Task {

    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }

    @Override
    public String fileText() {
        return "E " + super.fileText() + " | " + this.date;
    }
}