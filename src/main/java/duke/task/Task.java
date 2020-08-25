package duke.task;

public class Task {
    private String taskInfo;
    private TaskType taskType;
    protected boolean done;
    public static final int undoneNo = 0;
    public static final int doneNo = 1;

    public Task(String taskInfo, TaskType taskType) {
        this.taskInfo = taskInfo;
        this.taskType = taskType;
    }

    public Task doneTask() {
        this.done = true;
        return this;
    }

    public TaskType returnTaskType() {
        return taskType;
    }

    public int returnDoneStatus() {
        return done ? doneNo : undoneNo;
    }

    public String returnTaskInfo() {
        return taskInfo;
    }

    @Override
    public String toString() {
        String status = done ? "[✓]" : "[✗]";
        return taskType + status + " " + taskInfo;
    }
}