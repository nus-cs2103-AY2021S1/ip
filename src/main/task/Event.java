package main.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime time;
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");

    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public Event(String name, String time, boolean taskDoneState) {
        super(name, taskDoneState);
        this.time = LocalDateTime.parse(time);
    }

    @Override
    public String write() {
        return String.format("E,%s%s", time, super.write());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                time.format(FORMATTER));
    }
}
