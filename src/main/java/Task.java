import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String new_task;
    protected boolean isDone;
    protected String time;

    /**
     * Constructs a task object.
     * @param new_task Name of task.
     */
    public Task(String new_task) {
        this.new_task = new_task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done or completed.
     */
    public void set_Task_As_Done() {
        this.isDone = true;
    }

    /**
     * Return the file format form for the task.
     */
    public String fileFormat() {
        return this.getStatusIcon() + " | " + new_task;
    }

    /**
     * Return the converted time form of the task.
     */
    public String timeConverted() {
        return this.getStatusIcon() + " | " + new_task;

    }

    /**
     * To check whether the task contain the keyword searched by the user
     */
    public boolean containKeyWord(String keyword) {
        return new_task.contains(keyword);
    }
    
    
    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "] " + new_task;
    }

    
}
