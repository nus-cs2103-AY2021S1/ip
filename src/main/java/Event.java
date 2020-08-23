public class Event extends Task {
    String date;

    Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.description + "(at: " + this.date + ")";
    }
}
