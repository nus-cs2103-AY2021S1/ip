public class Event extends Task {

   protected String at;

    public Event(String description) {
        super(description);
    }
    String[] stringSegments = description.split("/at", 2);

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + stringSegments[0] + " (at:" + stringSegments[1] + ")";
    }
}