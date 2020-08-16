public class event extends Task {
    String dateTime;
    event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }
    public String toString() {
        return "[E]" + super.toString();
    }
}
