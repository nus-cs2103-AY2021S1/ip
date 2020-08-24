public class Event extends Task {

    String date;

    Event(String task, String date) {
        super(task);
        this.date = date;
    }

    Event(String task, boolean done, String date) {
        super(task);
        this.done = done;
        this.date = date;
    }

    @Override
    String getSaveString() {
        return "[D] " + super.getSaveString() + " /at " + this.date;
    }
    @Override
    public String toString() {
        return "[Event] " + super.toString() + " (at: " + this.date + ")";
    }
}
