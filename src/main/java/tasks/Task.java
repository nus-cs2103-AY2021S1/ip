package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a task with a datetime and description
 */
public class Task {
    private boolean done;
    private String work;
    private LocalDateTime date;

    /**
     * Initializes a task with the user given description
     * @param work A String with the user description
     */
    public Task(String work){
        this.done = false;
        this.work = work;
    }

    /**
     * Initializes a task with user given description and datetime
     * @param work A String containing the user given description
     * @param date A LocalDateTime object containing the timing of the deadline/task
     */
    public Task(String work, LocalDateTime date){
        this.work = work;
        this.date = date;
        this.done = false;
    }

    public String getDate(){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String description(){
        return done
                ? "[T][✓]"
                : "[T][✗]";
    }
    public void updateStatus(){
        done = true;
    }

    public boolean isDone(){
        return done;
    }

    public String getWork(){
        return work;
    }

    public boolean istodo(){
        return false;
    }

    public String toString(){
        if (!this.done){
            return "[✗]" + this.work;
        } else {
            return "[✓]" + this.work;
        }
    }
}
