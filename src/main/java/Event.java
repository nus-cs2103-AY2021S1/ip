import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public Event markAsDone() {
        Event doneEvent = new Event(this.description, this.at, true);
        System.out.println(" ____________________________________________________________\n " +
                "Nice! I've marked this task as done:\n    " +
                doneEvent.toString() +
                "\n ____________________________________________________________");
        return doneEvent;
    }

    @Override
    public String toTxtFileFormat() {
        return "E" + super.toTxtFileFormat() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }
}