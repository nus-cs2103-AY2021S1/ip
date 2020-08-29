package duke;

/**
 * The Task class to store task information
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Task {
    boolean isDone = false;
    private String name;
    private String time;

    /**
     * Task constructor to initialize a task object with the name
     * @param name name of task
     */
    Task(String name){
        this.name = name;
    }

    /**
     * Task constructor to initialize a task object with the name and time
     * @param name name of task
     * @param time date and time of task in the form of a string
     */
    Task(String name, String time){
        this.name = name;
        this.time = time;
    }

    public String getName(){
        return name;
    }

    public String getTime(){
        return time;
    }

    /**
     * completedTask method which marks the task as completed
     * @return the completed task
     */
    public Task completeTask(){
        isDone = true;
        return this;
    }

}
