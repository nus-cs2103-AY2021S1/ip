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
    public String writeToFile() {
        int done = isDone ? 1 : 0;
        return String.format("E//%d//%s//%s\n", done, this.description, this.time );
    }

    @Override
    public String getOutput() {
        return String.format("%s %s(At: %s)", getStatusIcon(), this.description, printTime());
    }
}
