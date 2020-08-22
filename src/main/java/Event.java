public class Event extends TimedTask {
    private static final String TAG = "[E]";

    public Event (String description, String at) {
        super(description, at, TAG);
    }
}
