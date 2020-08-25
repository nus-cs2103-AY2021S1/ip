import java.time.LocalDateTime;

public class Event extends Task {
<<<<<<< HEAD
    static char sym = '\u23F1';

    public Event(String desc, String time) {
        super(desc);
        symbol = sym;
        this.time = "(" + time + ")";
=======
    public Event(String desc, LocalDateTime time) {
        super(desc);
        symbol = "\u23F1";
        this.time = "(" + time.format(formatter) + ")";
>>>>>>> branch-Level-8
    }

    public Event(String desc) {
        super(desc);
        symbol = sym;
    }
}
