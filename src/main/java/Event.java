import java.time.LocalDateTime;

public class Event extends Task {

    static char sym = '\u23F1';

    public Event(String desc, LocalDateTime time) {
        super(desc);
        symbol = sym;
        this.time = "/" + time.format(formatter);

    }

    public Event(String desc) {
        super(desc);
        symbol = sym;
    }
}
