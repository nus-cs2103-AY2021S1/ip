public class Event extends Task {
    String time;
    public Event(String name, Status status, String time) {
        super(name, status);
        this.time = time;
    }

    @Override public String toString() {
        return "[E] " + this.status.statusToSymbol() + this.name + " at: " + time;
    }
}
