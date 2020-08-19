public class Event extends Task {
    String start;
    public Event(String in, String start) {

        super(in);
        this.start = start;
    }
    public String toString() {
        String donez;
        if (done) {
            donez = "✓";
        } else {
            donez = "✗";
        }
        return "[E] [" + donez + "] " + task + " (at: " + start + ")";
    }
}
