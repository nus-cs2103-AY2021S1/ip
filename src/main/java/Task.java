public class Task {
    protected boolean isDone;
    protected String description;
    public static int totalTasks = 0;
    protected TaskType taskType;

    Task(String description, TaskType taskType) {
        this.isDone = false;
        this.description = description;
        this.taskType = taskType;

    }

    void setDone() {
        isDone = true;
    }
    
    @Override
    public String toString() {
        if (this.isDone) {
            return taskType + "[✓] " + this.description;
        } else {
            return taskType + "[✘] " + this.description;
        }
    }
}
