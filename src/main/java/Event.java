public class Event extends Task {
    String time;

    Event(String s, String time) {
        super(s);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[event]" + super.toString() + "(at: " + this.time + ")";
    }
}
