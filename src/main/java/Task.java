/**
 * Task class contains information about the task.
 */
public class Task {
    protected Boolean isDone;
    protected String icon; //tick or cross
    protected String name;
    protected String taskType;
    
    private final static String CROSS = "X";
    private final static String TICK = "O";
    

    /**
     * Constructor to create a Task object.
     * @param name the description of the Task.
     */
    Task(String name) {
        this.name = name;
        this.isDone = false;
        this.icon = CROSS;
        this.taskType = "Task";
    }

    /**
     * Overloaded constructor to create a Task object, specifying the completion status
     * of the Task.
     * @param name the description of the Task.
     * @param isDone the completion status of the Task.
     */
    Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.icon = isDone ? TICK : CROSS;
        this.taskType = "Task";
    }
    
    /**
     * Returns a String in the format which the Task is saved.
     * @return a String in the format which the Task is saved.
     */
    public String encode() {
        return isDone
                ? String.format("%s | 1 | %s", taskType, name)
                : String.format("%s | 0 | %s", taskType, name);
    }

    /**
     * Marks Task as done.
     */
    public void markDone() {
        this.icon = TICK;
        this.isDone = true;
    }

    /**
     * Returns the name of the Task.
     * @return the name of the Task.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", icon, name);
    }
}
