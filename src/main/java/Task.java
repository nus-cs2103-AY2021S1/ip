public class Task {
    protected String task;
    protected TaskType taskType;
    private boolean done;

    public Task(String task, TaskType taskType) {
        this.task = task;
        this.taskType = taskType;
    }

    public Task doneTask() {
        this.done = true;
        return this;
    }

    @Override
    public String toString() {
        String status = done ? "[✓]" : "[✗]";
        return taskType + status + " " + task;
    }
}