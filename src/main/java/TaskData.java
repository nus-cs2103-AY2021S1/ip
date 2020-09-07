import com.sun.javafx.scene.shape.LineHelper;

import java.util.List;

/**
 * <p>The TaskData class is a data object that stores all information about a task.</p>
 */
public class TaskData {
    private final String taskType;
    private final String taskDescription;
    private final int isDone;
    private String date;
    private String time;
    private String tags;
    /**
     * Creates a TaskData object that stores data for a deadline task.
     *
     * @param taskType A String that represents task type.
     * @param taskDescription A String that represents task description.
     * @param isDone An int that represents the task status (0 is not done and 1 is done).
     * @param date A String that represent the day the task to be done by.
     * @param tags A String array that stores the tags.
     */
    public TaskData(String taskType, String taskDescription, int isDone, String date, String tags) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.time = date;
        this.tags = tags;
    }

    /**
     * Creates a TaskData object that stores data for a todo task.
     *
     * @param taskType A String that represents task type.
     * @param taskDescription A String that represents task description.
     * @param isDone An int that represents the task status (0 is not done and 1 is done).
     * @param tags A String array that stores the tags.
     */
    public TaskData(String taskType, String taskDescription, int isDone, String tags) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Creates a TaskData object that stores data for an event task.
     *
     * @param taskType A String that represents task type.
     * @param taskDescription A String that represents task description.
     * @param isDone An int that represents the task status (0 is not done and 1 is done).
     * @param date A String that represents the day the event happens.
     * @param time A String that represents the time the event happens.
     * @param tags A String array that stores the tags.
     */
    public TaskData(String taskType, String taskDescription, int isDone, String date, String time, String tags) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.date = date;
        this.time = time;
        this.tags = tags;
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

    public String getTags() {
        return tags;
    }
}
