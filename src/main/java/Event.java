import java.time.LocalDateTime;

/**
 * event
 */
public class Event extends Task {

    /**
     * constructor
     * @param desc task description
     * @param time local date time
     */
    public Event(String desc, LocalDateTime time) {
        super(desc);
        this.sym = EVENT_SYMBOL;
        this.time = time;

    }
}
