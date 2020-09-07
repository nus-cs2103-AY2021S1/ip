package butler.task;

public class Task {
    protected String summary;
    protected boolean isComplete;
    protected TaskType taskType;

    public Task(String summary) {
        this.summary = summary;
        this.isComplete = false;
        this.taskType = null;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void markComplete() {
        isComplete = true;
    }

    @Override
    public String toString() {
        return (isComplete ? "[Y]" : "[N]") + " " + getSummary();
    }
}
