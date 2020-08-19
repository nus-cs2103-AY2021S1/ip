public class Event extends Task {
    public Event(String desc, String time) {
        super(desc);
        symbol = "\u23F1";
        this.time = "(" + time + ")";
    }
}
