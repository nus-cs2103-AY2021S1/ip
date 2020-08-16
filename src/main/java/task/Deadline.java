package task;

public class Deadline extends Task {
    private final String datetime;

    private static final String DEADLINE = "[D]";
    public static final String DEADLINE_BREAK = "/by";

    Deadline(String description, boolean completed, String datetime) {
        super(description, completed);
        this.datetime = datetime;
    }

    public static Deadline createDeadline(String description, String datetime) {
        return new Deadline(description, false, datetime);
    }

    @Override
    public String toString() {
        String byDatetime = String.format("(by: %s)", this.datetime);
        return DEADLINE + toStringSuffix() + " " + byDatetime;
    }
}