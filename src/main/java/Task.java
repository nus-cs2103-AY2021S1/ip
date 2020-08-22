import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String content;
    private boolean completed = false;
    private TaskStatus status;
    private LocalDate time;

    public Task(String content, String status) {
        this.content = content;
        this.status = TaskStatus.valueOfStatus(status);
    }

    public Task(String content, String status, String time) {
        this.content = content;
        this.status = TaskStatus.valueOfStatus(status);
        this.time = LocalDate.parse(time);
    }

    public void markAsDone() {
        completed = true;
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        String statusLabel = "[" + status.toString().substring(0, 1) + "]";
        String mainBody = statusLabel + (completed ? "[✓]" : "[✗]") + " " + content;
        if (status == TaskStatus.EVENT) {
            mainBody += " (on: " + getTime() + ")";
        }
        if (status == TaskStatus.DEADLINE) {
            mainBody += " (by: " + getTime() + ")";
        }
        return mainBody;
    }
}
