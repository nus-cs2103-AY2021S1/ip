import java.util.HashMap;

public class Event extends Task {

    private String when = "";

    public Event(String name, String when) {
        super(name);
        this.when = when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.when + ")";
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> dict = super.convertToHashMap();
        dict.put("type", "Event");
        dict.put("when", this.when);
        return dict;
    }
}
