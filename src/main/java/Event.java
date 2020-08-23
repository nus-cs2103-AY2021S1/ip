import java.time.LocalDate;

public class Event extends Task{
    LocalDate time;
    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
    }

    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = LocalDate.parse(time);
    }

    String printTime() {
        return String.format("%s %d %d", time.getMonth(), time.getDayOfMonth(), time.getYear());
    }

    @Override
    public String getStatusIcon() {
        return String.format("[E]%s", super.getStatusIcon(), printTime());
    }

    @Override
    public String getOutput() {
        return String.format("%s %s%s", getStatusIcon(), this.description, this.time);
    }

}