import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate time;
    public Event(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    public String dataStorage() {
        return "E | " + (super.getStatus() == "\u2713" ? "1" : "0") + " | " + super.getTaskName() + " | " + this.time;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
