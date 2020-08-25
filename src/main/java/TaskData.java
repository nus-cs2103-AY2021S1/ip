/**
 * <p>The TaskData class is a data object that stores all information about a task.</p>
 */
public class TaskData {
    public final String taskType;
    public final String taskDescription;
    public final int isDone;
    public String date;
    public String time;

    /**
     * Creates a TaskData object that stores data for a deadline task.
     * @param taskType A String that represents task type
     * @param taskDescription A String that represents task description
     * @param isDone An int that represents the task status (0 is not done and 1 is done)
     * @param date A String that represent the day the task to be done by
     */
    public TaskData(String taskType, String taskDescription, int isDone, String date) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.time = date;
    }

    /**
     * Creates a TaskData object that stores data for a todo task.
     * @param taskType A String that represents task type
     * @param taskDescription A String that represents task description
     * @param isDone An int that represents the task status (0 is not done and 1 is done)
     */
    public TaskData(String taskType, String taskDescription, int isDone) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    /**
     * Creates a TaskData object that stores data for an event task.
     * @param taskType A String that represents task type
     * @param taskDescription A String that represents task description
     * @param isDone An int that represents the task status (0 is not done and 1 is done)
     * @param date A String that represents the day the event happens
     * @param time A String that represents the time the event happens
     */
    public TaskData(String taskType, String taskDescription, int isDone, String date, String time) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.date = date;
        this.time = time;
    }
}
