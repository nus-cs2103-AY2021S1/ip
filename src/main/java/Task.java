import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private boolean done;
    private String work;
    private LocalDateTime date;

    public Task(String work){
        this.done = false;
        this.work = work;
    }

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
