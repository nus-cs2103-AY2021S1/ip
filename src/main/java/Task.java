import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String new_task;
    protected boolean isDone;
    protected String time;

    public Task(String new_task) {
        this.new_task = new_task;
        this.isDone = false;
    }

//    public Task(String new_task, String time) {
//        this.new_task = new_task;
//        this.isDone = false;
//        this.time = time;
//    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    public void set_Task_As_Done() {
        this.isDone = true;
    }
    
    public String fileFormat() {
        return this.getStatusIcon() + " | " + new_task;
    }
<<<<<<< HEAD
=======

    public String timeConverted() {
        return this.getStatusIcon() + " | " + new_task;

    } 
    
    
>>>>>>> branch-Level-8
    
    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "] " + new_task;
    }

    
}
