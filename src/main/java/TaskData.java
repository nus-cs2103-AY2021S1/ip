public class TaskData {
    public final String taskType;
    public final String taskDescription;
    public final int isDone;
    public String date;
    public String time;


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
}
