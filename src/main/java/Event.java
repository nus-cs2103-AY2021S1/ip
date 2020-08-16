public class Event extends Task {
    private final String at;
    private final String TAG = "[E]";

    public Event (String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getDescription() {
        String message = TAG + super.getDescription() + " " + this.getAt();
        return message;
    }

    public String getAt() {
        return "(at: " + this.at + ")";
    }

}
