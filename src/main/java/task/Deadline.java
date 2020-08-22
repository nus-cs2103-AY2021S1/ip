package task;

import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private final Date datetime;

    public static final String DEADLINE_SYMBOL = "D";
    public static final SimpleDateFormat DATE_FORMAT_INPUT = new SimpleDateFormat("dd-MM-yyyy HHmm");
    public static final SimpleDateFormat DATE_FORMAT_OUTPUT = new SimpleDateFormat("MMM dd yyyy hh:mm a");
    public static final String DEADLINE_BREAK = "/by";
    public static final int COMMAND_LENGTH = 2;

    public Deadline(String description, boolean isCompleted, Date datetime) {
        super(description, isCompleted);
        this.datetime = datetime;
    }

    public static Deadline createDeadline(String description , Date datetime) {
        return new Deadline(description, false, datetime);
    }

    @Override
    public String toString() {
        String stringDateTime = DATE_FORMAT_OUTPUT.format(this.datetime);
        String byDatetime = String.format("(by: %s)", stringDateTime);
        return "[" + DEADLINE_SYMBOL + "]" + toStringSuffix() + " " + byDatetime;
    }

    @Override
    public String getTaskSymbol() {
        return DEADLINE_SYMBOL;
    }

    @Override
    public Optional<String> getTaskDatetime() {
        String stringTime = DATE_FORMAT_OUTPUT.format(this.datetime);
        return Optional.of(stringTime);
    }
}