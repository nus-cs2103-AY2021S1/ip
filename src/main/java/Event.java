public class Event extends Task {
    
    private String timing;

    public Event (String desc) {
        super(desc.split("event ")[1].split(" /at ")[0], "E");
        this.timing = desc.split("event ")[1].split(" /at ")[1];
    }

    public Event(String desc, String timing) {
        super(desc, "E");
        this.timing = timing;
    }

    @Override
    public String toString () {
        return super.toString() + " (at: " + timing + ")";
    }

    @Override
    public String getTiming() {
        return this.timing;
    }
}