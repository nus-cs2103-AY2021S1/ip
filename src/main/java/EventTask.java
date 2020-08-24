import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{

    private LocalDate time;

    public EventTask(String name, boolean isCompleted, String time) {
        super(name, isCompleted);
        this.time = LocalDate.parse(time);
    }

    public String getType(){
        return "E";
    }

    public String getTime(){
        return this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
