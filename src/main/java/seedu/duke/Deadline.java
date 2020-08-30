package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime time;

    public Deadline(String name, LocalDateTime time, boolean status) {
        super(name, status);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("d MMM yyyy kkmm")) + ")";
    }

    @Override
    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String getType() {
        return "D";
    }
}
