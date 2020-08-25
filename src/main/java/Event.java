public class Event extends Task {
    static char sym = '\u23F1';

    public Event(String desc, String time) {
        super(desc);
        symbol = sym;
        this.time = "(" + time + ")";
    }

    public Event(String desc) {
        super(desc);
        symbol = sym;
    }
}
