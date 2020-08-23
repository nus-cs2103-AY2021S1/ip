import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDate time;
    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
    }

    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        System.out.println(LocalDate.parse(time));
        this.time = LocalDate.parse(time);
    }

    String printTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getStatusIcon() {
        return String.format("[E]%s", super.getStatusIcon(), printTime());
    }

    @Override
    public String getOutput() {
        return String.format("%s %s%s", getStatusIcon(), this.description, printTime());
    }

}