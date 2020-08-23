public class TaskData {
    public final String taskType;
    public final String taskDescription;
    public final int isDone;
    public String time;

    public TaskData(String taskType, String taskDescription, int isDone, String time) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.time = time;
    }

    public TaskData(String taskType, String taskDescription, int isDone) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }
}
