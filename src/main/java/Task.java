public class Task {
    protected boolean isDone;
    protected String description;
    public static int totalTasks = 0;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.isDone = false;
        this.description = description;
        this.taskType = taskType;
    }

    public Task(String description, TaskType taskType, int done) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
        if (done == 1) {
            this.isDone = true;
        }
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
