import java.time.LocalDateTime;

public class Event extends Task {


    public Event(String desc, LocalDateTime time) {
        super(desc);
        this.sym = EVENT_SYMBOL;
        this.time = time;

    }
}
