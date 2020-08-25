public class Task {

    protected boolean isDone;
    protected String task;
    protected Tasktype tasktype;
    protected String duration;

    public Task(String task, Tasktype tasktype, String duration) {
        this.task = task;
        this.tasktype = tasktype;
        this.isDone = false;
        this.duration = duration;
    }

    public Task(String task, Tasktype tasktype, String duration, boolean isDone) {
        this.task = task;
        this.tasktype = tasktype;
        this.isDone = isDone;
        this.duration = duration;
    }

    public String getStatusIcon() {
        return isDone ? "✓" : "✘";
    }

    public void makeDone() {
        this.isDone = true;
    }

    public boolean isTaskDone() {
        return this.isDone;
    }

    public String getTask() {
        return task;
    }

    public String getDuration() {
        return duration;
    }

    public Tasktype getTasktype() {
        return tasktype;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.tasktype, getStatusIcon(), this.task);
    }

}
