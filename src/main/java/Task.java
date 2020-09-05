public class Task {
    protected String summary;
    protected boolean isComplete;
    protected TaskType taskType;

    public Task(String summary) {
        this.summary = summary;
        this.isComplete = false;
        this.taskType = null;
    }

    public String getStatus() {
        return (isComplete ? "[Y]" : "[N]");
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
        this.isComplete = true;
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + this.getSummary();
    }
}
