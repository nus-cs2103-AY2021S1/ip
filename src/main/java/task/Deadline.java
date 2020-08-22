package task;

public class Deadline extends Task {
    private final String datetime;

    private static final String DEADLINE_SYMBOL = "D";
    public static final String DEADLINE_BREAK = "/by";
    public static final int COMMAND_LENGTH = 2;

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
        return "[" + DEADLINE_SYMBOL + "]" + toStringSuffix() + " " + byDatetime;
    }
}