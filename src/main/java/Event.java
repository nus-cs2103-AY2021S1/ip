import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate time;
    public Event(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
