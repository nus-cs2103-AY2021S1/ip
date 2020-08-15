package task;

public class Event extends Task{
    private String time;

    private static String EVENT= "[D]";
    public static String EVENT_BREAK = "/at";

    Event(String task, boolean completed, String time) {
        super(task, completed);
        this.time = time;
    }

    @Override
    public String toString() {
        String atTime = String.format("(at: %s)", this.time);
        return EVENT + toStringSuffix() + " " + atTime;
    }
}
