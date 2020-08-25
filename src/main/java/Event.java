import java.time.LocalDateTime;

public class Event extends Task {
    public Event(String desc, LocalDateTime time) {
        super(desc);
        symbol = "\u23F1";
        this.time = "(" + time.format(formatter) + ")";
    }
}
