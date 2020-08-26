package Task;

import Task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final static DateTimeFormatter NEW_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mma");
    private final static DateTimeFormatter SAVE_READ_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public Deadline(String taskDescription) {
        super(taskDescription);
    }

    public void setTime(LocalDateTime givenDate) {
        date = givenDate;
    }

    @Override
    public String saveFormat() {
        String base = "[D] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription + "by:" + date.format(SAVE_READ_DATETIME_FORMAT);
        return base;
    }

    @Override
    public String toString() {
        String base = "[D] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription + "(by:" + date.format(NEW_DATETIME_FORMAT) + ")";
        return base;
    }
}
