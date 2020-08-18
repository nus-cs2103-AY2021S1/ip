public class Task {
    private String description;
    private boolean isCompleted;

    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    private String getStatusIcon() {
        if (isCompleted) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public String printTaskMessage() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
