public class Task {
    private String taskName;
    private boolean isComplete;
    public Task (String s) {
        taskName = s;
        isComplete = false;
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
}
