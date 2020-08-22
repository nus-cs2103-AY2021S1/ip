public class Event extends Task {

    private String time;

    Event(String name, String time) {
        super(name);
        this.time = time;
    }

    Event(String name, String time, boolean done) {
        super(name, done);
        this.time = time;
    }

    public String appendFile() {
        String doneString = (done == true ? "1" : "0");
        return "EVENT" + " | " + doneString + " | " + this.name + " | " + this.time;
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        return "[E]" + "[" + doneString + "] " + this.name + " (at: " + this.time + ")";
    }
}
