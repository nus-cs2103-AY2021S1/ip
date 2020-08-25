public class TaskData {
    private final String taskType;
    private final String taskDescription;
    private final int isDone;
    private String date;
    private String time;


    public TaskData(String taskType, String taskDescription, int isDone, String date) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.time = date;
    }

    public TaskData(String taskType, String taskDescription, int isDone) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public TaskData(String taskType, String taskDescription, int isDone, String date, String time) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.date = date;
        this.time = time;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getIsDone() {
        return isDone;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
