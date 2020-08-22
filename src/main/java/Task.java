public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toData() {
        int binary = isDone ? 1 : 0;
        return binary + " | " + description;
    }
    
    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s] %s", statusIcon, description);
    }
}