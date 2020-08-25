public class Task {
    private String taskInfo;
    private TaskType taskType;
    private String when;
    protected boolean done;
    protected static final int undoneNo = 0;
    protected static final int doneNo = 1;

    public Task(String taskInfo, TaskType taskType) {
        this.taskInfo = taskInfo;
        this.taskType = taskType;
    }

    public Task(String taskInfo, TaskType tasktype, String when) {
        this.taskInfo = taskInfo;
        this.taskType = tasktype;
        this.when = when;
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
    public String returnWhen() {
        return when;
    }

    @Override
    public String toString() {
        String status = done ? "[✓]" : "[✗]";
        return taskType + status + " " + taskInfo;
    }
}