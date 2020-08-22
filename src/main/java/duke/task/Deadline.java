package duke.task;

import duke.utils.Datetime;

import java.util.Optional;
import java.time.LocalDate;

public class Deadline extends Task {
    private final Datetime datetime;

    public static final String DEADLINE_SYMBOL = "D";
    public static final String DATE_FORMAT_INPUT = "dd-MM-yyyy HHmm";
    public static final String DATE_FORMAT_OUTPUT = "MMM dd yyyy hh:mm a";
    public static final String DEADLINE_BREAK = "/by";
    public static final int COMMAND_LENGTH = 2;

    public Deadline(String description, boolean isCompleted, LocalDate datetime) {
        super(description, isCompleted);
        this.datetime = new Datetime(datetime, DATE_FORMAT_INPUT, DATE_FORMAT_OUTPUT);
    }

    public static Deadline createDeadline(String description , LocalDate datetime) {
        return new Deadline(description, false, datetime);
    }

    @Override
    public String toString() {
        String stringDateTime = this.datetime.getOutputDatetimeString();
        String byDatetime = String.format("(by: %s)", stringDateTime);
        return "[" + DEADLINE_SYMBOL + "]" + toStringSuffix() + " " + byDatetime;
    }

    @Override
    public String getTaskSymbol() {
        return DEADLINE_SYMBOL;
    }

    @Override
    public Optional<String> getTaskDatetime() {
        String stringDateTime = this.datetime.getOutputDatetimeString();
        return Optional.of(stringDateTime);
    }
}