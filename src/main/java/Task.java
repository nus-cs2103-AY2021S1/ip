public class Task {
    private final String content;
    private boolean completed = false;
    private TaskStatus status;
    private String time = "";

    public Task(String content, String status) {
        this.content = content;
        this.status = TaskStatus.valueOfStatus(status);
    }

    public Task(String content, String status, String time) {
        this.content = content;
        this.status = TaskStatus.valueOfStatus(status);
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getStatus() {
        return status.getStatus();
    }

    public String getTime() {
        return time;
    }

    public void markAsDone() {
        completed = true;
    }

    @Override
    public String toString() {
        String statusLabel = "[" + status.toString().substring(0, 1) + "]";
        String mainBody = statusLabel + (completed ? "[✓]" : "[✗]") + " " + content;
        if (status == TaskStatus.EVENT) {
            mainBody += " (at: " + time + ")";
        }
        if (status == TaskStatus.DEADLINE) {
            mainBody += " (by: " + time + ")";
        }
        return mainBody;
    }
}
