public class Event extends Task {

    String date;

    Event(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[Event] " + super.toString() + " (at: " + this.date + ")";
    }
}
