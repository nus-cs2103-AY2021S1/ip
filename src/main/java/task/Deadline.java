package task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private final Date datetime;

    private static final String DEADLINE_SYMBOL = "D";
    public static final SimpleDateFormat DATE_FORMAT_INPUT = new SimpleDateFormat("dd-MM-yyyy HHmm");
    public static final SimpleDateFormat DATE_FORMAT_OUTPUT = new SimpleDateFormat("MMM dd yyyy hh:mm a");
    public static final String DEADLINE_BREAK = "/by";
    public static final int COMMAND_LENGTH = 2;

    Deadline(String description, boolean completed, Date datetime) {
        super(description, completed);
        this.datetime = datetime;
    }

    public static Deadline createDeadline(String description, Date datetime) {
        return new Deadline(description, false, datetime);
    }

    @Override
    public String toString() {
        String stringDateTime = DATE_FORMAT_OUTPUT.format(this.datetime);
        String byDatetime = String.format("(by: %s)", stringDateTime);
        return "[" + DEADLINE_SYMBOL + "]" + toStringSuffix() + " " + byDatetime;
    }
}