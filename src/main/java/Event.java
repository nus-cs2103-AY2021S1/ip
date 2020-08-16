public class Event extends Task{

    private String time;

    Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        return "[E]" + "[" + doneString + "] " + this.name + " (at: " + this.time + ")";
    }
}
