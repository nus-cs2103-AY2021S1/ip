public class EventTask extends Task {
    String time;
    public EventTask(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")" ;
    }
}
