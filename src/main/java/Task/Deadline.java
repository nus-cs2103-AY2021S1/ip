package Task;

import Task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final static DateTimeFormatter NEW_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mma");
    public Deadline(int position, String taskDescription) {
        super(position, taskDescription);
    }

    public void setTime(LocalDateTime givenDate) {
        date = givenDate;
    }

    @Override
    public String toString() {
        String base = "[D]";
        if (taskCompleted) {
            base = base + "[✓] ";
        } else {
            base = base + "[✗] ";
        }
        base = base + taskDescription + "(by:" + date.format(NEW_DATETIME_FORMAT) + ")";
        return base;
    }
}
