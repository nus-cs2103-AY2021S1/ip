public class Task {
    private String taskName;
    private boolean isComplete;
    public Task (String s, boolean isComplete) {
        this.taskName = s;
        this.isComplete = isComplete;
    }
    public String getTaskName() {
        return taskName;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public void setComplete(boolean status) {
        isComplete = status;
    }
    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "- | " + completion + " | " + this.getTaskName();
    }
}
