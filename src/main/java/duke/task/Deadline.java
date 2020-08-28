package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.storeAs = "D,0," + description + "," + by;
    }

    public Deadline(String done, String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if (done.equals("1")) {
            this.isDone = true;
            this.storeAs = "D,1," + description + "," + by;
        }
        this.storeAs = "D,1," + description + "," + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}