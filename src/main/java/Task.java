abstract class Task {
    protected String taskType;
    protected String description;
    protected boolean isDone;

    Task(String tasktype, String description) {
        this.taskType = tasktype;
        this.description = description;
        this.isDone = false;
    }

    protected void completeTask() {
        this.isDone = true;
    }

    protected String getStatusIcon() {
        return this.isDone ? "[✓] " : "[✗] ";
    }

    abstract public String formatTaskForFile();

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
