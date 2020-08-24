public class Event extends Task {

    public static String icon = "E";
    private String preposition;
    private String eventString;

    Event(String taskString, String preposition, String eventString) {
        super(taskString);
        this.preposition = preposition;
        this.eventString = eventString;
    }

    Event(String taskString, String preposition, String eventString, boolean status) {
        super(taskString);
        this.preposition = preposition;
        this.eventString = eventString;
        this.status = status;
    }

    @Override
    public String toString() {
        String statusIcon = (status)?"✓":"✗";
        return "[" + icon + "]" + "[" + statusIcon + "] "
                + this.taskString + " (" + this.preposition + ": " + this.eventString + ")";
    }

    @Override
    public String toDataString() {
        return "event/" + taskString + "/" + preposition + "/" + eventString + "/" + status;
    }
}
