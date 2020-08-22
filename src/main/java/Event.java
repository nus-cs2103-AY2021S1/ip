import java.util.HashMap;

public class Event extends Task {

    private TimePoint when;

    public Event(String name, String when) {
        super(name);
        this.when = TimePoint.of(when);
    }

    public Event(String name, String when, boolean done) {
        super(name, done);
        this.when = TimePoint.of(when);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.when.toString() + ")";
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> dict = super.convertToHashMap();
        dict.put("type", "Event");
        dict.put("when", this.when.toSaveString());
        return dict;
    }
}
