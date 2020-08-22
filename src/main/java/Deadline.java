public class Deadline extends TimedTask {
    private static final String TAG = "[D]";

    public Deadline (String description, String by) {
        super(description, by, TAG);
    }
}