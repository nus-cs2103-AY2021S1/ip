package duke;

/**
 * The Task class to store task information
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Task {
    private boolean isDone;
    private String name;
    private String time;
    private String date;

    /**
     * Task constructor to initialize a task object with the name
     * @param name name of task
     */
    Task(String name) {
        isDone = false;
        this.name = name;
        this.time = "";
        this.date = "";
    }

    /**
     * Task constructor to initialize a task object with the name and time
     * @param name name of task
     * @param time date and time of task in the form of a string
     */
    Task(String name, String date, String time) {
        isDone = false;
        this.name = name.replaceAll("\\s", "");
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public boolean getDone() {
        return isDone;
    }

    /**
     * completedTask method which marks the task as completed
     * @return the completed task
     */
    public Task completeTask() {
        isDone = true;
        return this;
    }

}
