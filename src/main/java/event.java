

public class event extends Task {
    String dateTime;
    public event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.dateTime + ")";
    }
}
